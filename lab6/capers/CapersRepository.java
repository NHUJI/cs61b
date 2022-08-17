package capers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static capers.Utils.*;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
//    生成.capers文件夹的目录地址
    static final File CAPERS_FOLDER = Utils.join(CWD, ".capers");
    static Dog ourDog;


    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        //创建.capers/
        //       - dogs/
        CAPERS_FOLDER.mkdir();
        Dog.DOG_FOLDER.mkdir();

    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text)  {
        File storyFile = Utils.join(CAPERS_FOLDER,"story.text");

        //检查文件是否存在
        if(storyFile.exists()){
            //String形式加载内容
            String readContents =Utils.readContentsAsString(storyFile);
            //支持多参数，所以我们不用手动相加String
            Utils.writeContents(storyFile, readContents,"\n",text);
        }else {
            try {
                storyFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //写入内容
            Utils.writeContents(storyFile, text);
        }
        System.out.println(Utils.readContentsAsString(storyFile));
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {

        ourDog = new Dog(name,breed,age);
        //存储Dog到文件夹
        ourDog.saveDog();
        System.out.println(ourDog.toString());

    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        // TODO
        ourDog = Dog.fromFile(name);
        ourDog.haveBirthday();
        ourDog.saveDog();



    }

}
