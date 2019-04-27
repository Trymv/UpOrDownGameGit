import java.util.ArrayList;

/**
 * This class hold on a list of rules.
 *
 * @author TrymV
 * @version 0.2
 */
public class RuleList {
    private ArrayList<Rule> listOfRules;
    private int maxNumberOfRules;

    /**
     * Constructor for object of the class RuleList.
     */
    public RuleList() {
        this.listOfRules = new ArrayList<>();
        this.maxNumberOfRules = 3;
    }

    /**
     * Sets the max number of rules you can have in the list.
     * @param maxNumberOfRules the new max number of rules.
     */
    public void setMaxNumberOfRules(int maxNumberOfRules) {
        this.maxNumberOfRules = maxNumberOfRules;
    }

    /**
     * Adds a rule to the listOfRules.
     * If the list has the same or more rules than maxNumberOfRules
     * the first added rule will be removed.
     * @param ruleToBeAdded the rule to be added to the list.
     * @throws IllegalArgumentException if ruleToBeAdded is set to null.
     */
    public void addRule(Rule ruleToBeAdded) {
        if (ruleToBeAdded != null) {
            if (isThereMoreThanXRules()) {
                removeOldestRule();
            }
            listOfRules.add(ruleToBeAdded);
        }
        else {
            throw new IllegalArgumentException("ruleToBeAdded was set to null in addRule.");
        }
    }

    /**
     * Removes the rule with index 0 witch is the oldest rule in the list.
     */
    private void removeOldestRule() {
        Rule ruleToBeRemoved = null;

        for (Rule rule : listOfRules) {
            if (listOfRules.indexOf(rule) == 0) {
                ruleToBeRemoved = rule;
            }
        }
        if (ruleToBeRemoved != null) {
            listOfRules.remove(ruleToBeRemoved);
        }
    }

    /**
     * Will check if the size of the listOfRules is the same size or bigger than maxNumberOfRules.
     * If the size is the same or bigger this method will return true.
     * The use of this method is to check before adding a new rule.
     * Therefor it also checks if the size is the same so the list never has
     * more rules than maxNumberOfRules.
     * X = maxNumberOfRules
     * @return true if listOfRules size is equal or higher than maxNumberOfRules.
     */
    private boolean isThereMoreThanXRules() {
        boolean moreThanXRules = false;

        if(listOfRules.size() >= maxNumberOfRules) {
            moreThanXRules = true;
        }
        return moreThanXRules;
    }

    /**
     * List the index +1, name and description of all rules in listOfRules
     * with StringBuilder and return them as a string.
     * @return index +1, name and a description of all rules in listOfRules as a String.
     */
    public String listAllRuleDescriptions() {
        StringBuilder ruleDescriptions = new StringBuilder();

        for(Rule rule:listOfRules) {
            ruleDescriptions.append("\nRule").append(listOfRules.indexOf(rule)+1).append(": ").append(rule.getName()).append("\n").append(rule.getDescription()).append("\n");
        }
        return ruleDescriptions.toString();
    }
}
