package eapli.base.ordersmanagement.answer.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnswerService {

    private final AnswerRepository answerRepository = PersistenceContext.repositories().answer();

    public int universe(String id){
        List<Answer> answers = answerRepository.universe(id);
        return answers.size();
    }

    public int answers(String id){
        List<Answer> answers = answerRepository.universe(id);
        Map<String, String> map = new HashMap<>();
        for (Answer a: answers
             ) {
            map = a.getEvery_Answer();
        }
        map.entrySet().removeIf(entry -> entry.getValue().equalsIgnoreCase("NOT ANSWERED"));
        return map.size();
    }

}
