// src/main/java/com/uc/ticketingsystem/service/CommentService.java
package com.uc.ticketingsystem.service;

import com.uc.ticketingsystem.dto.CommentDto;
import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long ticketId, String externalUserId);
    List<CommentDto> getCommentsByTicketId(Long ticketId);
    void deleteComment(Long commentId);
    // this should be good for now as well might not need anything else - maybe in the future we add a way to update the comment but that could be an additional feature
}