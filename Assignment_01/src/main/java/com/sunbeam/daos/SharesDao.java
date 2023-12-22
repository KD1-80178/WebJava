package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojos.Review;

public class SharesDao extends Dao{
    protected SharesDao() throws Exception {
		
	}
    public List<Review> sharedWithMe(int userId) throws Exception {
        List<Review> sharedReviews = new ArrayList<>();
        String sql = "SELECT r.* FROM reviews r JOIN shares s ON r.id = s.review_id WHERE s.user_id = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int reviewId = rs.getInt("id");
                    int movieId = rs.getInt("movie_id");
                    String reviewText = rs.getString("review");
                    int rating = rs.getInt("rating");
                    Timestamp modified = rs.getTimestamp("modified");
                    // Instantiate Reviews object and populate the list
                    Review review = new Review(reviewId, movieId, reviewText, rating, userId,modified);
                    review.setModified(modified);
                    sharedReviews.add(review);
                }
            }
        }
        
        return sharedReviews;
    }
    //
    public int shareReviewWithUser(int reviewId, int userId) throws Exception {
        String sql = "INSERT INTO shares (review_id, user_id) VALUES (?, ?)";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            stmt.setInt(2, userId);
            return stmt.executeUpdate();
        }
    }
}

