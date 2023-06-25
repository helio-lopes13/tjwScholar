package br.edu.ifce.tjwScholar.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.edu.ifce.tjwScholar.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.edu.ifce.tjwScholar.thymeleaf.processor.MenuAttributeTagProcessor;
import br.edu.ifce.tjwScholar.thymeleaf.processor.MessageElementTagProcessor;
import br.edu.ifce.tjwScholar.thymeleaf.processor.OrderElementTagProcessor;
import br.edu.ifce.tjwScholar.thymeleaf.processor.PaginationElementTagProcessor;;

public class ScholarDialect extends AbstractProcessorDialect {

	public ScholarDialect() {
		super("TJW Scholar", "scholar", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
