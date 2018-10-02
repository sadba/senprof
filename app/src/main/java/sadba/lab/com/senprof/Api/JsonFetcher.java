package sadba.lab.com.senprof.Api;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

public class JsonFetcher {
    private JsonReaderFromResources r;

    public JsonFetcher(Context c, int id) throws IOException {
        r = new JsonReaderFromResources(c, id);
    }

    @Override
    public String toString() {
        String s = "";
        try {
            s = r.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    private class JsonReaderFromResources {
        private Context context;
        private int resource;
        private Writer writer = new StringWriter();

        public JsonReaderFromResources(Context ctx, int resource_id) {
            this.context = ctx;
            this.resource = resource_id;
        }

        public String read() throws IOException {

            InputStream resourceReader = context.getResources().openRawResource(resource);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
                String line = reader.readLine();
                while (line != null) {
                    writer.write(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                resourceReader.close();
            }
            return writer.toString();
        }
    }
}
