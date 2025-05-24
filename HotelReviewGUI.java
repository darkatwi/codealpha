import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;

class Review {
    String hotel;
    int rating;
    String text;

    public Review(String hotel, int rating, String text) {
        this.hotel = hotel;
        this.rating = rating;
        this.text = text;
    }

    public Object[] toRow() {
        return new Object[] { hotel, rating, text };
    }
}

public class HotelReviewGUI extends JFrame {
    ArrayList<Review> reviews = new ArrayList<>();
    JTextField hotelField, filterField;
    JTextArea reviewArea;
    JComboBox<Integer> ratingBox;
    JTable reviewTable;
    DefaultTableModel tableModel;

    public HotelReviewGUI() {
        setTitle("Hotel Review System");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        hotelField = new JTextField();
        reviewArea = new JTextArea(3, 20);
        ratingBox = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5 });

        inputPanel.add(new JLabel("Hotel Name:"));
        inputPanel.add(hotelField);
        inputPanel.add(new JLabel("Rating (1-5):"));
        inputPanel.add(ratingBox);
        inputPanel.add(new JLabel("Review:"));
        inputPanel.add(new JScrollPane(reviewArea));

        JButton addButton = new JButton("Add Review");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Table panel
        tableModel = new DefaultTableModel(new Object[] { "Hotel", "Rating", "Review" }, 0);
        reviewTable = new JTable(tableModel);
        add(new JScrollPane(reviewTable), BorderLayout.CENTER);

        // Button panel
        JPanel bottomPanel = new JPanel();
        JButton showAllButton = new JButton("Show All");
        JButton sortHighButton = new JButton("Sort High → Low");
        JButton sortLowButton = new JButton("Sort Low → High");
        JButton filterButton = new JButton("Filter");

        filterField = new JTextField(10);

        bottomPanel.add(showAllButton);
        bottomPanel.add(sortHighButton);
        bottomPanel.add(sortLowButton);
        bottomPanel.add(new JLabel("Filter by Hotel:"));
        bottomPanel.add(filterField);
        bottomPanel.add(filterButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        addButton.addActionListener(e -> addReview());
        showAllButton.addActionListener(e -> refreshTable(reviews));
        sortHighButton.addActionListener(e -> sortReviews(true));
        sortLowButton.addActionListener(e -> sortReviews(false));
        filterButton.addActionListener(e -> filterReviews());

        setVisible(true);
    }

    void addReview() {
        String hotel = hotelField.getText().trim();
        String text = reviewArea.getText().trim();
        int rating = (int) ratingBox.getSelectedItem();

        if (hotel.isEmpty() || text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields.");
            return;
        }

        reviews.add(new Review(hotel, rating, text));
        hotelField.setText("");
        reviewArea.setText("");
        refreshTable(reviews);
    }

    void refreshTable(ArrayList<Review> list) {
        tableModel.setRowCount(0);
        for (Review r : list) {
            tableModel.addRow(r.toRow());
        }
    }

    void sortReviews(boolean highToLow) {
        reviews.sort((r1, r2) -> highToLow ? r2.rating - r1.rating : r1.rating - r2.rating);
        refreshTable(reviews);
    }

    void filterReviews() {
        String filter = filterField.getText().trim().toLowerCase();
        ArrayList<Review> filtered = new ArrayList<>();
        for (Review r : reviews) {
            if (r.hotel.toLowerCase().contains(filter)) {
                filtered.add(r);
            }
        }
        refreshTable(filtered);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HotelReviewGUI::new);
    }
}
