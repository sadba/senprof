package sadba.lab.com.senprof.Remote;

public class Common {

    public static final String BASE_URL = "https://planeteapi.education.sn/";

    public static IMyAPI getAPI()
    {
        return RetrofitClient.getClient(BASE_URL).create(IMyAPI.class);
    }
}
