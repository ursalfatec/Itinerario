package br.com.itinerario.persists;

import java.util.List;

public interface IDAO<T> {
	public void create(Object o) throws Exception;
	public void update(Object o) throws Exception;
	public void delete(Object o) throws Exception;
	public List<T> listar() throws Exception;
}
