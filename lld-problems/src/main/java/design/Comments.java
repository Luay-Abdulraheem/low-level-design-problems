package design;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Comments {

    LocalDateTime now = LocalDateTime.now();
    private List<CommentDto> commentsList = new ArrayList<>();

    CommentDto commentParent1 = new CommentDto("1", 1L, "Parent 1", "", new ArrayList<>(), now);
    CommentDto commentChild1Parent1 = new CommentDto("2", 2L, "Child 1 for Parent 1", "1", new ArrayList<>(), now.plusMinutes(1));
    CommentDto commentParent2 = new CommentDto("3", 3L, "Parent 2", "", new ArrayList<>(), now.plusMinutes(2));
    CommentDto commentChild2Parent1 = new CommentDto("4", 4L, "Child 2 for Parent 1", "1", new ArrayList<>(), now.plusMinutes(3));
    CommentDto commentChild1Parent2 = new CommentDto("5", 4L, "Child 1 for Parent 2", "3", new ArrayList<>(), now.plusMinutes(4));

    public void rearrangeComments() {

        commentsList.add(commentChild1Parent2);
        commentsList.add(commentChild2Parent1);
        commentsList.add(commentParent2);
        commentsList.add(commentChild1Parent1);
        commentsList.add(commentParent1);

        commentsList.forEach(commentDto -> System.out.println(commentDto.id));

        Map<String, List<CommentDto>> commentMap = commentsList.stream().collect(Collectors.groupingBy(CommentDto::getParentCommentId));
        List<CommentDto> returnList = new ArrayList<>();
        if (!commentMap.isEmpty()) {
            List<CommentDto> parentList = new ArrayList<>(commentMap.get(""));
            for (CommentDto commentFindDto : parentList) {
                if (commentMap.containsKey(commentFindDto.getId())) {
                    commentFindDto.setChildComments(commentMap.get(commentFindDto.getId()).stream().sorted(Comparator.comparing(CommentDto::getCreatedAt)).toList());
                }
                returnList.add(commentFindDto);
            }
        }

        for (CommentDto commentDto: returnList) {
            System.out.println(commentDto.id);

            if (!commentDto.childComments.isEmpty()) {
                for (CommentDto commentDto1: commentDto.childComments) {
                    System.out.println(commentDto1.id);
                }
            }
        }
    }

    class CommentDto {
        private String id;
        private Long userId;
        private String body;
        private String parentCommentId;
        private List<CommentDto> childComments;
        private LocalDateTime createdAt;

        public CommentDto(String id, Long userId, String body, String parentCommentId, List<CommentDto> childComments, LocalDateTime createdAt) {
            this.id = id;
            this.userId = userId;
            this.body = body;
            this.parentCommentId = parentCommentId;
            this.childComments = childComments;
            this.createdAt = createdAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getParentCommentId() {
            return parentCommentId;
        }

        public void setParentCommentId(String parentCommentId) {
            this.parentCommentId = parentCommentId;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public List<CommentDto> getChildComments() {
            return childComments;
        }

        public void setChildComments(List<CommentDto> childComments) {
            this.childComments = childComments;
        }
    }
}
