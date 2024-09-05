package com.example.Comment_service.service;

import com.example.Comment_service.model.Comment;
import com.example.Comment_service.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Create a new comment
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Retrieve all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Retrieve a comment by ID
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // Update a comment
    public Comment updateComment(Long id, Comment commentDetails) {
        // Fetch the existing comment by ID
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();

            // Update the fields with new values from commentDetails
            existingComment.setContent(commentDetails.getContent());  // Assuming Comment has a 'text' field
            existingComment.setAuthorId(commentDetails.getAuthorId()); // Assuming Comment has an 'author' field
            // Update other fields as necessary

            // Save the updated comment back to the repository
            return commentRepository.save(existingComment);
        } else {
            throw new RuntimeException("Comment not found with id " + id);
        }
    }

    // Delete a comment by ID
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
