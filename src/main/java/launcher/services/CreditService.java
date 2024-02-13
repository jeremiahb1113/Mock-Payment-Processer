package launcher.services;

import launcher.models.CreditCard;
import launcher.models.User;
import launcher.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService{

    @Autowired
    public CreditRepository creditRepository;



    public void makeCard(CreditCard card){

        creditRepository.save(card);
    }

    public void addCard(Long id, CreditCard card) {

        creditRepository.appendCard(card.getCard_number(),
                card.getCcv(),card.isActive(),card.isEnabled(),card.getFunds(),card.getCard_type(), id);
    }

    public void deleteCard(Long id) {
        creditRepository.deleteById(id);
    }

    public void activeCard(Long id) {
        creditRepository.activateCard(id);
    }

    public void deactiveCard(Long id) {
        creditRepository.deactivateCard(id);
    }

    public void enableCard(Long id) {
        creditRepository.enableCard(id);
    }

    public void disableCard(Long id) {
        creditRepository.disableCard(id);
    }

    public void pay(Long id, Double funds){
        creditRepository.subtract(funds,id);
    }

    public void deposit(Long id, Double funds){
        creditRepository.add(funds,id);
    }


}
