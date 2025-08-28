package com.devsuperior.bds02.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private CityRepository cityRepository;

	@Transactional(readOnly = true)
	public List<EventDTO> findAll() {
		List<Event> result = eventRepository.findAll(Sort.by("name"));
		return result.stream().map(x -> new EventDTO(x)).toList();
	}

	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event entity = new Event();
		if (!cityRepository.existsById(dto.getCityId())) {
			throw new ResourceNotFoundException("Cidade não cadastrada");
		} else {
			BeanUtils.copyProperties(dto, entity);
			@SuppressWarnings("deprecation")
			City city = cityRepository.getById(dto.getCityId());
			entity.setCity(city);
			entity = eventRepository.save(entity);
			return new EventDTO(entity);
		}
	}
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event entity = eventRepository.getReferenceById(id);
			City city = cityRepository.findById(dto.getCityId())
				.orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada"));
			entity.setCity(city);
			entity.setDate(dto.getDate());
			entity.setId(id);
			entity.setName(dto.getName());
			entity.setUrl(dto.getUrl());
			entity = eventRepository.save(entity);
			return new EventDTO(entity);
		}  catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Evento não encontrado");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!eventRepository.existsById(id)) {
			throw new ResourceNotFoundException("Evento não encontrado");
		}
		try {
			eventRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}
}
