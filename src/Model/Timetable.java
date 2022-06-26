package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Random;

public class Timetable {
	private final ObservableList<RoomModel> rooms;
	private final ObservableList<TrainersModel> trainers;
	private final ObservableList<StylesModel> styles;
	private final ObservableList<GroupsModel> groups;
	private final ObservableList<TimeslotsModel> timeslots;
	private ClassModel classes[];
	private StylesModel style;
	private int numClasses = 0;

	/**
	 * Initialize new Timetable
	 *
	 */
	public Timetable(ObservableList<RoomModel> roommodel, ObservableList<TrainersModel> trainers, ObservableList<StylesModel> styles, ObservableList<GroupsModel> groups, ObservableList<TimeslotsModel> timeslots) {
		this.rooms = roommodel;
		this.trainers = trainers;
		this.styles = styles;
		this.groups = groups;
		this.timeslots = timeslots;
	}
	public void generatedTimetable() {


		// Initialize GA
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);

		// Initialize population
		Population population = ga.initPopulation(this);

		// Evaluate population
		ga.evalPopulation(population, this);

		// Keep track of current generation
		int generation = 1;
		// Start evolution loop
		while (ga.isTerminationConditionMet(generation, 1000) == false
				&& ga.isTerminationConditionMet(population) == false) {
			// Print fitness
			System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());
			// Apply crossover
			population = ga.crossoverPopulation(population);
			// Apply mutation
			population = ga.mutatePopulation(population, this);
			// Evaluate population
			ga.evalPopulation(population, this);
			// Increment the current generation
			generation++;
		}
		// Print fitness
		this.createClasses(population.getFittest(0));
		System.out.println();
		System.out.println("Solution found in " + generation + " generations");
		System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
		System.out.println("Clashes: " + this.calcClashes());
		// Print classes
		System.out.println();
		ClassModel classes[] = this.getClasses(); // получили расписание, как его вывести
//		int classIndex = 1;
//		for (ClassModel bestClass : classes) {
//			int id = classIndex;
//			String room = String.valueOf(timetable.getRoom(bestClass.getRoomId()).getRoomNumber());
//			String trainer = String.valueOf(timetable.getProfessor(bestClass.getTrainerId()).getTrainerName());
//			String style = String.valueOf(timetable.getStyle(bestClass.getStyleId()).getStyleId());
//			int group = Integer.valueOf(timetable.getGroup(bestClass.getGroupId()).getGroupId());
//			int groupShift = Integer.valueOf(timetable.getGroup(bestClass.getGroupId()).getGroupShift());
//			String timeslot = String.valueOf(timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
//			generatedTableData.add(new GeneratorTimetable(id,style,groupShift,group,room,trainer,timeslot));
//			classIndex++;
//		}
	}

	public Timetable(Timetable cloneable) {
		this.rooms = (ObservableList<RoomModel>) cloneable.getRooms();
		this.trainers = (ObservableList<TrainersModel>) cloneable.getProfessors();
		this.styles = (ObservableList<StylesModel>) cloneable.getStyles();
		this.groups = (ObservableList<GroupsModel>) cloneable.getGroups();
		this.timeslots = (ObservableList<TimeslotsModel>) cloneable.getTimeslots();
	}


	private ObservableList<GroupsModel> getGroups() {
		return this.groups;
	}
	private ObservableList<RoomModel> getRooms() {
		return this.rooms;
	}

	private ObservableList<TimeslotsModel> getTimeslots() {
		return this.timeslots;
	}

	private ObservableList<StylesModel> getStyles() {
		return this.styles;
	}

	private ObservableList<TrainersModel> getProfessors() {
		return this.trainers;
	}

	/**
	 * Add new room
	 *
	 * @param roomId
	 * @param roomName
	 * @param capacity
	 */
	public void addRoom(int roomId, String roomName, int capacity) {
		this.rooms.add(new RoomModel(roomId, roomName, capacity));
	}

//	/**
//	 * Add new professor
//	 *
//	 * @param professorId
//	 * @param professorName
//	 */
	public void addTrainer(int trainerId, String trainerName) {
		this.trainers.add(new TrainersModel(trainerId, trainerName));
	}

//	/**
//	 * Add new module
//	 *
//	 * @param moduleId
//	 * @param moduleCode
//	 * @param module
//	 * @param professorIds
//	 */
	public void addStyle(int styleId, String styleCode, String style, int trainersIds[]) {
		this.styles.add(new StylesModel(styleId, styleCode, style, trainersIds));
	}

//	/**
//	 * Add new group
//	 *
//	 * @param groupId
//	 * @param groupSize
//	 * @param moduleIds
//	 */
	public void addGroup(int groupId, int groupShift, int groupSize, int[] styleId) {
		this.groups.add(new GroupsModel(groupId, groupShift, groupSize, styleId));
		this.numClasses = 0;
	}

	/**
	 * Add new timeslot
	 *
	 * @param timeslotId
	 * @param timeslot
	 */
	public void addTimeslot(int timeslotId, int timeslotShift, String timeslot) {
		this.timeslots.add(new TimeslotsModel(timeslotId, timeslotShift, timeslot));
	}

	/**
	 * Create classes using individual's chromosome
	 *
	 * @param individual
	 */
	public void createClasses(Individual individual) {
		// Init classes
		ClassModel classes[] = new ClassModel[this.getNumClasses()];

		// Get individual's chromosome
		int chromosome[] = individual.getChromosome();
		int chromosomePos = 0;
		int classIndex = 0;

		for (GroupsModel group : this.getGroupsAsArray()) {
			int[] styleIds = group.getStyleIds();
			for (int styleId : styleIds) {
				classes[classIndex] = new ClassModel(classIndex, group.getGroupId(), styleId);

				// Add timeslot
				classes[classIndex].addTimeslot(chromosome[chromosomePos]);
				chromosomePos++;

				// Add room
				classes[classIndex].setRoomId(chromosome[chromosomePos]);
				chromosomePos++;

				// Add professor
				classes[classIndex].addTrainer(chromosome[chromosomePos]);
				chromosomePos++;

				classIndex++;
			}
		}

		this.classes = classes;
	}
	/**
	 * Get room from roomId
	 *
	 * @param roomId
	 * @return room
	 */
	public RoomModel getRoom(int roomId) {
		if (!this.rooms.stream().filter(o -> o.getRoomId()==(roomId)).findFirst().isPresent()) {
			System.out.println("Rooms doesn't contain key " + roomId);
		}
		return (RoomModel) this.rooms.get(roomId);
	}


	/**
	 * Get random room
	 *
	 * @return room
	 */
	public RoomModel getRandomRoom() {
		Random rand = new Random();
		RoomModel randomElement = this.rooms.get(rand.nextInt(this.rooms.size()));
		return randomElement;
	}

//	/**
//	 * Get professor from professorId
//	 *
//	 * @param professorId
//	 * @return professor
//	 */
	public TrainersModel getProfessor(int trainersId) {
		return (TrainersModel) this.trainers.get(trainersId);
	}

//	/**
//	 * Get module from moduleId
//	 *
//	 * @param moduleId
//	 * @return module
//	 */
	public StylesModel getStyle(int styleId) {
		return (StylesModel) this.styles.get(styleId);
	}

	/**
	 * Get moduleIds of student group
	 *
	 * @param groupId
	 * @return moduleId array
	 */
	public int[] getGroupModules(int groupId) {
		GroupsModel group = (GroupsModel) this.groups.get(groupId);
		return group.getStyleIds();
	}

	/**
	 * Get group from groupId
	 *
	 * @param groupId
	 * @return group
	 */
	public GroupsModel getGroup(int groupId) {
		return (GroupsModel) this.groups.get(groupId);
	}

	/**
	 * Get all student groups
	 *
	 * @return array of groups
	 */
	public GroupsModel[] getGroupsAsArray() {
		return (GroupsModel[]) this.groups.toArray(new GroupsModel[this.groups.size()]);
	}

	/**
	 * Get timeslot by timeslotId
	 *
	 * @param timeslotId
	 * @return timeslot
	 */
	public TimeslotsModel getTimeslot(int timeslotId) {
		return (TimeslotsModel) this.timeslots.get(timeslotId);
	}

	/**
	 * Get random timeslotId
	 *
	 * @return timeslot
	 */
	public TimeslotsModel getRandomTimeslot() {
		Object[] timeslotArray = this.timeslots.toArray();
		TimeslotsModel timeslot = (TimeslotsModel) timeslotArray[(int) (timeslotArray.length * Math.random())];
		return timeslot;
	}

//	public StringProperty classStyle(){
//		return new SimpleStringProperty(styles.get(getStyleName()));
//	}

	/**
	 * Get classes
	 *
	 * @return classes
	 */
	public ClassModel[] getClasses() {
		return this.classes;
	}

	/**
	 * Get number of classes that need scheduling
	 *
	 * @return numClasses
	 */
	public int getNumClasses() {
		if (this.numClasses > 0) {
			return this.numClasses;
		}

		int numClasses = 0;
		GroupsModel groups[] = (GroupsModel[]) this.groups.toArray(new GroupsModel[this.groups.size()]);
		for (GroupsModel group : groups) {
			numClasses += group.getStyleIds().length;
		}
		this.numClasses = numClasses;

		return this.numClasses;
	}
	
	/**
	 * Calculate the number of clashes
	 *
	 * @return numClashes
	 */
	public int calcClashes() {
		int clashes = 0;
		for (ClassModel classA : this.classes) {
			// Check room capacity
			int roomCapacity = this.getRoom(classA.getRoomId()).getRoomCapacity();
			int groupSize = this.getGroup(classA.getGroupId()).getGroupSize();
			if (roomCapacity < groupSize) {
				clashes++;
			}
			// Check shift timeslot
			int groupShift = this.getGroup(classA.getGroupId()).getGroupShift();
			int timeslotShift = this.getTimeslot(classA.getTimeslotId()).getTimeslotShift();
			if (groupShift != timeslotShift) {
				clashes++;
			}	
			// Check if room is taken
			for (ClassModel classB : this.classes) {
				if (classA.getRoomId() == classB.getRoomId() && classA.getTimeslotId() == classB.getTimeslotId() && classA.getClassId() != classB.getClassId()) {
						clashes++;
						break;
						}

			}
			// Check if professor is available
			for (ClassModel classB : this.classes) {
				if (classA.getTrainerId() == classB.getTrainerId()
						&& classA.getTimeslotId() == classB.getTimeslotId()
						&& classA.getClassId() != classB.getClassId()) {
					clashes++;
					break;
				}
			}
			
			
		}
		return clashes;
	}


}
