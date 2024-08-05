package qt.rw.challenge.blog_post.utils;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import qt.rw.challenge.blog_post.models.Comment;
import qt.rw.challenge.blog_post.models.Post;
import qt.rw.challenge.blog_post.models.UserData;

public class Mapper {

    public static ModelMapper modelMapper = new ModelMapper();
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static UserData getUserFromDTO(Object object, String password) {
        UserData user = getUserFromDTO(object);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }


    public static String encode(String raw){
        return passwordEncoder.encode(raw);
    }

    public static boolean compare(String encoded, String raw){
        return passwordEncoder.matches(raw, encoded);
    }



    public static UserData getUserFromDTO(Object object) {
        return modelMapper.map(object, UserData.class);
    }

    public static Post getPostFromDTO(Object object) {
        return modelMapper.map(object, Post.class);
    }
    public static Comment getCommentfromDTO(Object object) {
        return modelMapper.map(object, Comment.class);
    }

}
