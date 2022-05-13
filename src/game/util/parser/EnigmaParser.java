package game.util.parser;

import game.enigma.AnswerEnigma;
import game.enigma.Enigma;
import game.enigma.QCMEnigma;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A JSON parser for the file containing enigmas
 */
public class EnigmaParser {

    /**
     * Parse the given file
     * @param filepath the path to the file to parse
     * @return a list of the enigmas in the file
     * @throws IOException when an error occur during reading/writing
     * @throws ParseException when a parse error occur
     */
    public List<Enigma> parse(String filepath) throws IOException, ParseException {
        JSONObject json = (JSONObject) new org.json.simple.parser.JSONParser().parse(new FileReader(filepath));
        JSONArray qcmEnigmas = (JSONArray) ((JSONObject)json.get("enigmas")).get("qcm");
        JSONArray answerEnigmas = (JSONArray) ((JSONObject)json.get("enigmas")).get("answer");

        List<Enigma> enigmas = new ArrayList<>();
        enigmas.addAll(this.parseQCMEnigmas(qcmEnigmas));
        enigmas.addAll(this.parseAnswerEnigmas(answerEnigmas));

        return enigmas;
    }

    /**
     * Parse the part in the file for QCM enigmas
     * @param json the json array containing the QCM enigmas
     * @return the list of QCM enigmas
     */
    private List<Enigma> parseQCMEnigmas(JSONArray json) {
        List<Enigma> enigmas = new ArrayList<>();

        for (Object obj : json) {
            JSONObject jobj = (JSONObject) obj;
            String question = (String) jobj.get("question");
            List<String> answers = new ArrayList<>();
            String trueAnswer = "";

            for (Object ans : (JSONArray) jobj.get("answers")) {
                JSONObject answer = (JSONObject) ans;
                answers.add((String) answer.get("value"));

                if (answer.get("isAnswer").equals("true")) {
                    trueAnswer = (String) answer.get("value");
                }
            }

            enigmas.add(new QCMEnigma(question, answers, trueAnswer));
        }

        return enigmas;
    }

    /**
     * Parse the part in the file for answer enigmas
     * @param json the json array containing the answer enigmas
     * @return the list of answer enigmas
     */
    private List<Enigma> parseAnswerEnigmas(JSONArray json) {
        List<Enigma> enigmas = new ArrayList<>();

        for (Object obj : json) {
            JSONObject jobj = (JSONObject) obj;
            String question = (String) jobj.get("question");
            String answer = (String) jobj.get("answer");

            enigmas.add(new AnswerEnigma(question, answer));
        }

        return enigmas;
    }
}
