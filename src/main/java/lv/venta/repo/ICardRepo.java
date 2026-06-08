package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Card;

public interface ICardRepo extends CrudRepository<Card, Long>{

}
