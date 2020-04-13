package br.nom.penha.bruno.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;

public class RegistroCucumber implements TypeRegistryConfigurer {

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public void configureTypeRegistry(TypeRegistry typeRegistry) {
		typeRegistry.defineParameterType(
				new ParameterType<>("data", ".*", Date.class, (String s) -> {
					try {
						return new SimpleDateFormat("dd/MM/yyyy").parse(s);
					} catch (ParseException e) {
						return null;
					}
				})
		);
	}

}
