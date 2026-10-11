package com.ashish.bookstore.controller;

import com.ashish.bookstore.model.BookRequest;
import com.ashish.bookstore.repository.BookRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class BookRequestController {

    @Autowired
    private BookRequestRepository bookRequestRepository;

    // 1. Endpoint for USERS to request a missing book
    // Call using: POST /api/requests/submit?bookName=Atomic Habits
    @PostMapping("/submit")
    public String requestBook(@RequestParam String bookName) {
        BookRequest request = new BookRequest();
        request.setRequestedBookName(bookName);
        // We don't need to set the status or date here because
        // your model automatically sets them to "requested" and LocalDateTime.now()

        bookRequestRepository.save(request);

        return "Your request for '" + bookName + "' has been successfully submitted.";
    }

    // 2. Endpoint for ADMIN/LIBRARIAN to view all pending requests
    // Call using: GET /api/requests/admin/pending
    @GetMapping("/admin/pending")
    public List<BookRequest> getPendingRequests() {
        // We use "requested" here because that is the default status in your model
        return bookRequestRepository.findByStatus("requested");
    }

    // Endpoint for ADMIN/LIBRARIAN to update request status
    // Call using: PUT /api/requests/admin/1/status?status=approved
    @PutMapping("/admin/{id}/status")
    public String updateRequestStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<BookRequest> optionalRequest = bookRequestRepository.findById(id);

        if (optionalRequest.isPresent()) {
            BookRequest request = optionalRequest.get();
            request.setStatus(status); // e.g., "approved" or "rejected"
            bookRequestRepository.save(request);
            return "Request ID " + id + " has been updated to: " + status;
        }
        return "Request not found.";
    }
}