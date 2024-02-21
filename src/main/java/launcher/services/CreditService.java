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

        creditRepository.appendCard(card.getId(),card.getCard_number(),
                card.getCcv(),card.isActive(),card.isEnabled(),card.getFunds(),card.getCard_type(), id);
    }

    public void deleteCard(String id) {
        creditRepository.deleteById(id);
    }

    public void activeCard(String id) {
        creditRepository.activateCard(id);
    }

    public void deactiveCard(String id) {
        creditRepository.deactivateCard(id);
    }

    public void enableCard(String id) {
        creditRepository.enableCard(id);
    }

    public void disableCard(String id) {
        creditRepository.disableCard(id);
    }

    public void pay(String id, Double funds){
        creditRepository.subtract(funds,id);
    }

    public void deposit(String id, Double funds){
        creditRepository.add(funds,id);
    }

    public double checkFunds(String id){
        return creditRepository.funds(id);
    }

    public boolean checkCardExist(String id){
        return creditRepository.existsById(id);
    }


    public boolean isActive(String id) {
        return creditRepository.checkActive(id);
    }

    public boolean isEnabled(String id) {
        return creditRepository.checkEnabled(id);
    }

    public boolean isCredit(String id) {
        if(creditRepository.checkIsCredit(id).equalsIgnoreCase("CREDIT")) return true;
        else return false;
    }
}
