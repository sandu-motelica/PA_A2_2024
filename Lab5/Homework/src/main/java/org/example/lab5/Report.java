package org.example.lab5;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.example.lab5.exception.CommandExecutionException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Report implements Command{
    private Repository repository;

    public Report() {
    }

    public Report(Repository rep){
        this.repository = rep;
    }

    public void setRepository(String rep){
        this.repository = new Repository(rep);
    }
    public void execute() throws CommandExecutionException, IOException {

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("repositoryPath", repository.getDirectoryPath());
        dataModel.put("repositoryContent", repository.getRepositoryContent());
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        String currentDirectory = System.getProperty("user.dir");
        FileTemplateLoader templateLoader = new FileTemplateLoader(new File(currentDirectory));
        cfg.setTemplateLoader(templateLoader);

        Template template = cfg.getTemplate("template.ftl");

        StringWriter out = new StringWriter();
        try {
            template.process(dataModel, out);
        } catch (TemplateException e) {
            throw new CommandExecutionException("Error processing FreeMarker template", e);
        }

        String reportFileName = "report.html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFileName))) {
            writer.write(out.toString());
        }

        System.out.println("HTML report generated: " + reportFileName);
    }

    @Override
    public String toString() {
        return "Report{" +
                "repository=" + repository +
                '}';
    }
}

