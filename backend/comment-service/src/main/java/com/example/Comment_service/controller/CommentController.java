package com.example.Comment_service.controller;

import com.example.Comment_service.model.Comment;
import com.example.Comment_service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Comments")
public class CommentController {

    public final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
        Comment newComment = commentService.createComment(comment);
        return ResponseEntity.ok(newComment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(){

        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id){
        return commentService.getCommentById(id)
                .map(ResponseEntity:: ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id,@RequestBody Comment commentDetails){
        Comment updateComment = commentService.updateComment(id,commentDetails);
        return ResponseEntity.ok(updateComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
