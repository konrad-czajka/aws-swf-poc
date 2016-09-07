package konradczajka.aws.swfpoc.activities;

import java.util.List;

public class VideoActivitiesImpl implements VideoActivities {

    @Override
    public String encode(String source, String profile) {
        System.out.println(String.format("Encoding '%s' with profile '%s'", source, profile));
        return source.replace(".mp4", "") + profile + ".mp4";

    }

    @Override
    public List<String> packageWithMpegDash(List<String> sources) {
        System.out.println();
        System.out.println(String.format("Packaging '%d' files with MPEG-Dash", sources.size()));
        System.out.println();
        List<String> packageFiles = sources;
        packageFiles.add("manifest.mpd");
        return packageFiles;
    }
}
