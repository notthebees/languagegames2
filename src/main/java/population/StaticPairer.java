package population;

import java.util.ArrayList;
import java.util.List;

public class StaticPairer implements AgentPairer {

    private final List<Integer> interactionSpecs = new ArrayList<Integer>();

    public StaticPairer(final List<Integer> interactionSpecs) {
        this.interactionSpecs.addAll(interactionSpecs);
    }

    public List<Integer> pairingOrder(final int numAgents) {
        final List<Integer> pairingOrder = new ArrayList<Integer>();
        for (int i = 0; i < numAgents; i++) {
            pairingOrder.add(interactionSpecs.remove(0));
        }
        return pairingOrder;
    }

}
