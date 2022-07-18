package com.tes.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.tes.model.Unit;
import com.tes.services.environmentalofficer.UnitServices;

@RestController
@RequestMapping("/rest/testapi")
public class ApiDemoController
{

	@Autowired
	UnitServices unitServices;

	// CRUD

	// Create - POST method
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/create")
	private Unit create(@RequestBody List<Unit> unit)
	{
		for (Unit unit2 : unit)
		{
			unitServices.save(unit2);
		}
		return null;
	}

	@GetMapping("/{id}")
	private Unit readByid(@PathVariable("id") int unitId)
	{
		return unitServices.findByUnitId(unitId);
	}

	// Read - GET method
	@GetMapping
	private List<Unit> read()
	{
		return unitServices.findAll();
	}

	// Update - PUT method
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{id}")
	private int update(@PathVariable("id") int unitId)
	{
		return unitServices.updateUnitByUnitId(unitId);
	}

	// Delete - DELETE method
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	private List<Unit> delete(@PathVariable("id") int unitId)
	{
		return unitServices.deleteByUnitId(unitId);
	}

	/*
	 * https://dzone.com/articles/leverage-http-status-codes-to-build-a-rest-service
	 * POST /api/books Create a book 201
	 * GET /api/books/{isbn} Read a book 200
	 * PUT /api/books/{isbn} Update a book 200
	 * DELETE /api/books/{isbn} Delete a book 204
	 * PATCH /api/books/{isbn} Update book description 200
	 * GET /api/books Retrieve all books by pagination, sorting and ordering 200, 204, 206
	 */
}
