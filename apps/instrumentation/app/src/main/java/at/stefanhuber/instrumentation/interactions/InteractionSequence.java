package at.stefanhuber.instrumentation.interactions;

import java.util.List;

public class InteractionSequence extends Interaction {

    protected List<Interaction> interactions;
    protected long[] durations;

    public InteractionSequence(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    public boolean updateSequenceItem(int index, Interaction interaction) {
        if (interactions.size() > index && index >= 0) {
            interactions.remove(index);
            interactions.add(index, interaction);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void interact() {
        for (Interaction interaction : interactions) {
            interaction.interact();
        }
    }

    @Override
    public void start() {
        durations = new long[interactions.size()];
        duration = 0;

        for (int i = 0; i < interactions.size(); i++) {
            interactions.get(i).start();
            durations[i] = interactions.get(i).getDuration();
            duration += durations[i];
        }
    }

    public long[] getDurations() {
        return durations;
    }

    public String getPrintableDurations() {
        String out = "";

        for (int i = 0; i < interactions.size(); i++) {
            if (i > 0) {
                out += "\n";
            }
            out += interactions.get(i).getClass().getSimpleName() + ":" + durations[i];
        }

        return out;
    }

}
