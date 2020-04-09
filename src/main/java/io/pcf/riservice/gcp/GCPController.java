package io.pcf.riservice.gcp;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.storage.GoogleStorageResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/gcp-storage")
public class GCPController {

    @Value("gs://dvn-hdm-test/my-file.txt")
    private Resource gcsFile;

    @Value("gs://gfptepvtopsmgrbackup/state.yml")
    private Resource opsManFile;


    @RequestMapping(value = "/readtestbucket", method = RequestMethod.GET)
    public String readGcsFile() throws IOException {
        return StreamUtils.copyToString(
                this.gcsFile.getInputStream(),
                Charset.defaultCharset()) + "\n";
    }

    @RequestMapping(value = "/writetestbucket/{data}", method = RequestMethod.GET)
    String writeGcs(@PathVariable String data) throws IOException {
        try (OutputStream os = ((WritableResource) this.gcsFile).getOutputStream()) {
            os.write(data.getBytes());
        }
        return "file was updated with data : " + data + " \n";
    }

    @RequestMapping(value = "/readplatformbucket", method = RequestMethod.GET)
    public String readGcsFileOpsman() throws IOException {
        return StreamUtils.copyToString(
                this.opsManFile.getInputStream(),
                Charset.defaultCharset()) + "\n";
    }

    @RequestMapping("/environmentvalues")
    public String env() throws IOException {
        String message = "";
        for (Map.Entry<String, String> envvar : System.getenv().entrySet()) {
            message += envvar.getKey() + ": " + envvar.getValue() + "\n";
        }
        System.out.println("message = " + message);
        return message;
    }
}
