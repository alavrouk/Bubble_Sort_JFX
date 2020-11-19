import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * This class creates a visualization for the BubbleSort algorithm.
 *
 * @author alavrouk
 * @version 1.0
 */
public class BubbleSort extends Application {
    private int index = 0;
    private final StackPane root = new StackPane();

    /**
     * This method recreates the chart every frame.
     * @param array This is the array of integers that creates the chart
     * @param root This is the root pane that the chart is added to in order to be displayed
     */
    public void createChart(int[] array, StackPane root) {
        root.getChildren().clear();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setAnimated(true);
        chart.setCategoryGap(0);
        chart.setBarGap(0);
        chart.setTitle("Sorting");
        chart.setAnimated(true);
        chart.setHorizontalGridLinesVisible(false);
        chart.setVerticalGridLinesVisible(false);
        xAxis.setLabel("Array Index");
        yAxis.setLabel("Numerical Value");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        for (int i = 0; i < array.length; i++) {
            int index = i + 1;
            series1.getData().add(new XYChart.Data<>(Integer.toString(index), array[i]));
        }
        chart.getData().add(series1);
        root.getChildren().add(chart);
    }

    /**
     * This is the frame-by-frame bubblesort algorithm.
     * @param array This is the array that is being sorted
     * @param i This is the index at which the sort is happening
     */
    public void bubbleSort(int[] array, int i) {
        int temp;
        if (array[i] > array[i + 1]) {
            temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
        index++;
        createChart(array, root);
    }

    /**
     * This creates a random array with length of 100.
     * @return Returns the random array
     */
    public int[] createRandomArray() {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = (int) Math.floor(Math.random() * Math.floor(500)) + 50;
        }
        return array;
    }

    /**
     * This is the method that creates the stage which contains the graph and the timer that updates it.
     * @param primaryStage This is the primary stage for the program
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bubble Sort");
        int[] array = createRandomArray();
        createChart(array, root);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (index == array.length - 1) {
                    index = 0;
                }
                bubbleSort(array, index);
            }
        };
        Scene primaryScene = new Scene(root, 1600, 950);
        primaryStage.setScene(primaryScene);
        timer.start();
        primaryStage.show();
    }

    /**
     * This is the main method which launches the JavaFX program.
     * @param args These are the command line arguments with which the program is launched
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

}
