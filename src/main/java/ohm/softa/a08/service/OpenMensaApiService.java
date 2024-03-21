package ohm.softa.a08.service;

import com.google.gson.Gson;
import ohm.softa.a08.api.OpenMensaAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class OpenMensaApiService {
	private static final OpenMensaApiService instance;
	private OpenMensaAPI mensaApiInstance;

	/* initialize Retrofit instance */
	Retrofit retrofit = new Retrofit.Builder()
		.addConverterFactory(GsonConverterFactory.create(new Gson()))
		.baseUrl("http://openmensa.org/api/v2/")
		.build();

	static {
		instance = new OpenMensaApiService();
	}

	private OpenMensaApiService() { }

	public static OpenMensaApiService getInstance() {
		return instance;
	}

	public OpenMensaAPI getApi() {
		if (mensaApiInstance == null) {
			mensaApiInstance = retrofit.create(OpenMensaAPI.class);
		}

		return mensaApiInstance;
	}
}
