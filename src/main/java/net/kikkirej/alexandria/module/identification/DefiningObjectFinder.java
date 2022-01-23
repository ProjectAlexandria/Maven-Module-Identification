package net.kikkirej.alexandria.module.identification;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Component
public class DefiningObjectFinder {

    public String semicolonSeparatedPomDirectoriesIn(File analysisFolder, String filePattern){
        Set<File> moduleDirectories = new HashSet<>();
        analyzeForMavenModules(analysisFolder, moduleDirectories, filePattern);
        return foundFilesToShortendStrings(analysisFolder, moduleDirectories);
    }

    private void analyzeForMavenModules(File analysisFolder, Set<File> moduleDirectories, String filePattern) {
        File[] files = analysisFolder.listFiles();
        assert files != null;
        for (File file:
                files) {
            if(file.isDirectory()){
                analyzeForMavenModules(file, moduleDirectories, filePattern);
            }else if(file.getName().equalsIgnoreCase("pom.xml")){
                moduleDirectories.add(analysisFolder);
            }
        }
    }

    private String foundFilesToShortendStrings(File analysisFolder, Set<File> moduleDirectories) {
        String result = "";
        for (File moduleDirectory :
                moduleDirectories) {
            String modulePathAbsolute = moduleDirectory.getAbsolutePath();
            String moduleSubPath = modulePathAbsolute.replace(analysisFolder.getAbsolutePath(), "");
            moduleSubPath = moduleSubPath.replace('\\', '/');
            if(moduleSubPath.isEmpty()){
                moduleSubPath = "/";
            }
            result += moduleSubPath + ';';

        }
        if(result.length()>0){
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
