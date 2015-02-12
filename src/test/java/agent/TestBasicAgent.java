package agent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;

import org.junit.Test;

import conceptualspace.PerceptualObject;
import conceptualspace.Point;
import conceptualspace.SimpleObject;

public class TestBasicAgent {

	private final double someWeight = 0.42;

	@Test
	public void incrementsWeight() {
		final BasicAgent agent = new BasicAgent(0.5, new ArrayList<Concept>());
		final Agent newAgent = agent.incrementWeight(0.1);
		assertThat(newAgent.weight(), equalTo(0.6));
	}

	@Test
	public void getsConvergenceFromPairsOfCorrespondingConcepts() {
		final BasicAgent agent = new BasicAgent(
				someWeight,
				new Concept(new Point(0.2), 0.7),
				new Concept(new Point(0.5), 0.5));
		final Agent other = new BasicAgent(
				someWeight,
				new Concept(new Point(0.4), 0.4),
				new Concept(new Point(0.7), 0.8));

		assertThat(agent.convergenceWith(other), equalTo(0.35));
	}

	@Test
	public void classifiesAccordingToMostAppropriateConcept() {
		final BasicAgent agent = new BasicAgent(
				someWeight,
				new Concept(new Point(0.2), 0.7),
				new Concept(new Point(0.5), 0.5));

		final PerceptualObject material0 = new SimpleObject(new Point(0.35));
		final PerceptualObject material1 = new SimpleObject(new Point(0.4));

		assertThat(agent.classify(material0), equalTo(new Assertion(material0, 0, someWeight)));
		assertThat(agent.classify(material1), equalTo(new Assertion(material1, 1, someWeight)));
	}

	@Test
	public void learnsByUpdatingAppropriateConcept() {
		final BasicAgent agent = new BasicAgent(
				someWeight,
				new Concept(new Point(0.42), 0.42),
				new Concept(new Point(0.5), 0.5));

		final Agent updatedAgent = new BasicAgent(
				someWeight,
				new Concept(new Point(0.42), 0.42),
				new Concept(new Point(0.74), 0.8));

		assertThat(agent.learn(new Assertion(new SimpleObject(new Point(0.9)), 1, 0.8)),
				equalTo(updatedAgent));
	}

}
