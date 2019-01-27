package org.cwld.pett;

    import com.jcabi.manifests.Manifests;

public class VersionInfo {

    public String getVersion() {
        return Manifests.read("Application-Version");
    }
}
