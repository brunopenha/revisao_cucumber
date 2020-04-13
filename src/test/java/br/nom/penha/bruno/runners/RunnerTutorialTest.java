package br.nom.penha.bruno.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/aprender_cucumber.feature",
		glue = "br.nom.penha.bruno.steps",
		tags = {"@tipo1, @tipo2"},
		plugin = "pretty", 
		monochrome = true, 
		snippets = SnippetType.CAMELCASE, 
		dryRun = false, 
		strict = false)
public class RunnerTutorialTest {

}
