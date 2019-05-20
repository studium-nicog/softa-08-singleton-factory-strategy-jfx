package ohm.softa.a08.controller;

import com.google.gson.Gson;
import javafx.scene.control.*;
import ohm.softa.a08.api.OpenMensaAPI;
import ohm.softa.a08.filtering.MealFilterFactory;
import ohm.softa.a08.model.Meal;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ohm.softa.a08.service.OpenMensaAPIService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for main.fxml
 *
 * @author Peter Kurfer
 */
public class MainController implements Initializable {

	/**
	 * Logger e.g. to debug multi-threading issues
	 */
	private static final Logger logger;

	/**
	 * DateFormat instance to generate required date format string for OpenMensa API
	 */
	private static final DateFormat openMensaDateFormat;

	private final ObservableList<Meal> meals;

	/**
	 * Binding of ChoiceBox UI element to filter for certain types of meals
	 */
	@FXML
	private ChoiceBox<String> filterChoiceBox;

	/**
	 * Binding of ListView UI element to display meals
	 */
	@FXML
	private ListView<Meal> mealsListView;


	@FXML
	private Spinner<Integer> spinner;

	/*
	  static initializer to initialize fields in class
	 */
	static {
		logger = LogManager.getLogger(MainController.class);
		openMensaDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
	}

	/**
	 * Default constructor
	 */
	public MainController() {
		meals = FXCollections.observableArrayList();
	}

	/**
	 * Initialization method of the UI controller
	 * Called after the FXML fields are assigned
	 *
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mealsListView.setItems(meals);
		filterChoiceBox.setItems(FXCollections.observableList(Arrays.asList(new Gson().fromJson(new InputStreamReader(getClass().getResourceAsStream("/filters.json")), String[].class))));
		filterChoiceBox.getSelectionModel().selectFirst();


		SpinnerValueFactory<Integer> valueFactory = //
			new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);
		spinner.setValueFactory(valueFactory);
	}

	/**
	 * Handles fetching of meals from OpenMensa API
	 */
	@FXML
	public void doGetMeals() {
		var currentFilterName = filterChoiceBox.getSelectionModel().getSelectedItem();
		logger.debug(String.format("Selected filter is: %s", currentFilterName));
		var filter = MealFilterFactory.getStrategy(currentFilterName);

		/* get API instance from service singleton to execute call */
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, spinner.getValue());
		OpenMensaAPIService.getInstance().getApi().getMeals(openMensaDateFormat.format(c.getTime())).enqueue(new Callback<>() {
			@Override
			public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
				logger.debug("Got response");
				if (!response.isSuccessful() || response.body() == null) {
					logger.error(String.format("Got response with not successfull code %d", response.code()));

					Platform.runLater(() -> {
						/* Show an error message if the HTTP status code is not 2xx */
						var alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText("Unsuccessful HTTP call");
						alert.setContentText("Failed to get meals from OpenMensaAPI");
						alert.show();
					});
					return;
				}
				Platform.runLater(() -> {
					/* filter meals according to the selected filter */
					meals.clear();
					meals.addAll(filter.filter(response.body()));
				});
			}

			@Override
			public void onFailure(Call<List<Meal>> call, Throwable t) {
				logger.error("Failed to fetch meals");
				Platform.runLater(() -> {
					var alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText("Failed HTTP call");
					alert.setContentText("Failed to submit HTTP call to fetch meals.");
					alert.show();
				});
			}
		});
	}
}
