/*
1. Users should be able to comment to posts
2. Should be able to reply to comments
3. At most one level of nesting is allowed. Any further nested comment shall appear below the previous comment.
4. Users should be able to edit and delete comments. The nesting should adjust accordingly.
 */

import java.util.ArrayList;

enum CommentType{COMMENT,REPLY};

class Comment{
    String id;
    String userId;
    Comment parentComment;
    CommentType commentType;
    String postId;
    String content;
    ArrayList<Comment> replies;

    public Comment(String userId, String postId, String content) {
        this.id = getUniqueId();
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.replies = new ArrayList<Comment>();
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    void addCommentReplies(Comment reply){
        if (this.commentType==CommentType.COMMENT) {
            this.replies.add(reply);
            reply.setParentComment(this);
        } else {
            this.parentComment.addCommentReplies(reply);
        }
    }
    void setContent(String new_content){
        this.content = new_content;
    }
    static int commentid = 1;
    static String getUniqueId(){
        return Integer.toString(commentid++);
    }
}

class Post{
    String id;
    String content;
    ArrayList<Comment> comments;
    void addComment(String userId, String content){
        Comment c = new Comment(userId, this.id, content);
        this.comments.add(c);
    }
    void editComment(){}
    void deleteComment(){}

    public Post(String content) {
        this.id = getUniqueId();
        this.content = content;
        this.comments = new ArrayList<Comment>();
    }

    static int postid = 1;
    static String getUniqueId(){
        return Integer.toString(postid++);
    }
}

class User{
    String id;
    String name;
    void addCommentToPost(){}
    void replyToComment(){}
    void editComment(){}
    void deleteComment(){}

    static int userid = 1;
    static String getUniqueId(){
        return Integer.toString(userid++);
    }
}

public class FacebookCommentSystem {

}
