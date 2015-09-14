package ua.kiev.prog;

import java.util.List;

public interface AdvDAO {
	List<Advertisement> list();
    List<Advertisement> list(String pattern);
	void add(Advertisement adv);
    void delete(long id);
    byte[] getPhoto(long id);
}
