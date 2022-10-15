package com.yletter.auto2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.github.badamowicz.sonar.hla.api.HLAMeasure;
import com.github.badamowicz.sonar.hla.api.IProject;
import com.github.badamowicz.sonar.hla.api.ISonarConverter;
import com.github.badamowicz.sonar.hla.api.ISonarExtractor;
import com.github.badamowicz.sonar.hla.impl.SonarHLAFactory;

public class X201701_ExtractProjectsExample {

static List output = new ArrayList();

public static void main(String[] a) {

List projectKeys = new ArrayList();
projectKeys.add("project_key");

output.add("Header");
String url = "http://host_url";
extract(url, projectKeys);

System.out.println("\n");
for (String line : output)
System.out.println(line);
}

public static void extract(String url, List projectKeys) {

ISonarExtractor extractor = null;
ISonarConverter converter = null;
List projects = null;
String csvData = null;

extractor = SonarHLAFactory.getExtractor(url);
converter = SonarHLAFactory.getConverterInstance();

projects = new ArrayList();

for (String projectKey : projectKeys) {
projects.add(extractor.getProject(projectKey));
}

csvData = converter.getCSVData(projects, Arrays.asList(HLAMeasure.values()), true);
output.add(csvData.replace(";", ","));
}
}
