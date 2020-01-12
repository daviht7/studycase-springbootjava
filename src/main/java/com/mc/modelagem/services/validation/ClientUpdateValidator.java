package com.mc.modelagem.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.mc.modelagem.domain.Cliente;
import com.mc.modelagem.exceptions.FieldMessage;
import com.mc.modelagem.repositories.ClienteRepository;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, Cliente> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClientUpdate ann) {

	}

	@Override
	public boolean isValid(Cliente objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map =(Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer id = Integer.parseInt(map.get("id"));
		
		Cliente clienteFounded = clienteRepository.findByEmail(objDto.getEmail());

		if(clienteFounded != null && !clienteFounded.getId().equals(id)) {
			list.add(new FieldMessage("Email","E-mail já existe."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}

}
