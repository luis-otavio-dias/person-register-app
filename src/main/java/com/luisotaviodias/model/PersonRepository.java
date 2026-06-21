package com.luisotaviodias.model;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
	private final List<Person> items = new ArrayList<>();

	public void save(Person person) {
		items.add(person);
	}

	public List<Person> listAll() {
		return new ArrayList<>(items);
	}

	public void clearAll() {
		items.clear();
	}

	public boolean existsCpf(String cpf) {
		if (cpf == null)
			return false;
		for (Person p : items) {
			if (cpf.equals(p.getCpf()))
				return true;
		}
		return false;
	}
}
