package com.ufund.api.ufundapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.UfundDAO;

import java.util.logging.Level;

/**
 * Handles the REST API requests and responses for the UFund API
 *
 * @author E3M
 */
@RestController
@RequestMapping("")
public class UfundController {
	private static final Logger LOG = Logger.getLogger(UfundController.class.getName());
	private UfundDAO dao;

	/**
	 * Create a new UfundController using the given DAO
	 * @param dao the UfundDAO to use
	 */
	public UfundController(UfundDAO dao) {
		this.dao = dao;
	}

	@GetMapping({"/needs"})
	public ResponseEntity<Need[]> getNeeds() throws IOException {
		LOG.info("GET /needs");
        try {
			Need[] needs = this.dao.getNeeds();
			return new ResponseEntity<>(needs, HttpStatus.OK);
		} catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	@GetMapping({"/needs/"})
	public ResponseEntity<Need[]> searchNeeds(@RequestParam String name) throws IOException {
		LOG.info("GET /needs/?name=" + name);
        try {
			Need[] needs = this.dao.searchNeeds(name);
			return new ResponseEntity<>(needs, HttpStatus.OK);
		} catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	@GetMapping("/needs/{name}")
    public ResponseEntity<Need> getNeed(@PathVariable String name) {
        LOG.info("GET /needs/" + name);
        try {
            Need hero = dao.getNeed(name);
            if (hero != null)
                return new ResponseEntity<Need>(hero,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PostMapping({"/needs"})
   	public ResponseEntity<Need> createNeed(@RequestBody Need need) throws IOException {
        try {
			LOG.info("POST /needs " + String.valueOf(need));
			Need newNeed = this.dao.createNeed(need);
			if(newNeed == null) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<>(newNeed, HttpStatus.CREATED);
			}
		} catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
   	}

	@PutMapping({"/needs"})
	public ResponseEntity<Need> updateNeed(@RequestBody Need need) throws IOException {
		try {
			LOG.info("PUT /needs " + String.valueOf(need));
			if(this.dao.updateNeed(need) == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	@DeleteMapping({"/needs/{name}"})
   	public ResponseEntity<Need> deleteNeed(@PathVariable String name) throws IOException {
    	LOG.info("DELETE /need/" + name);
		try {
			if(!this.dao.deleteNeed(name)) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }
}
