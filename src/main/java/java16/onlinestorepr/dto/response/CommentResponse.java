package java16.onlinestorepr.dto.response;

import java16.onlinestorepr.model.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class CommentResponse {
    private Long id;
    private String comment;
    private LocalDateTime createdAt;
    private String username;

    public static CommentResponse of(Comment comment) {
     return    CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .createdAt(comment.getCreatedDate())
                .username(comment.getUser().getFirstName() + " " + comment.getUser().getLastName())
                .build();
    }

    public static List<CommentResponse> ofList(List<Comment> comments) {
        return comments.stream().map(CommentResponse::of).toList();
    }

}
