package konradczajka.aws.swfpoc.activities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class VideoActivitiesImpl implements VideoActivities {

    @Override
    public URL encode(URL source, String profile) {
        try {
            return new URL(source.getProtocol(), source.getHost(), source.getFile().replace(".mp4", "") + profile + ".mp4");
        } catch (MalformedURLException ex) {
            throw new RuntimeException("encoding failure");
        }
    }

    @Override
    public List<URL> packageWithMpegDash(List<URL> sources) {
        List<URL> packageFiles = sources;
        try {
            packageFiles.add(
                    new URL(
                            sources.get(0).getProtocol(),
                            sources.get(0).getHost(),
                            "manifest.mpd"
                    )
            );
        } catch (MalformedURLException ex) {
            throw new RuntimeException("packaging failure");
        }

        return packageFiles;
    }
}
