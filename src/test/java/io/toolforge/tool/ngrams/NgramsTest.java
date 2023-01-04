package io.toolforge.tool.ngrams;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Test;
import com.google.common.io.Resources;
import com.sigpwned.spreadsheet4j.SpreadsheetFactory;
import com.sigpwned.spreadsheet4j.model.TabularWorksheetReader;
import io.toolforge.tool.ngrams.App.NgramCount;
import io.toolforge.toolforge4j.io.InputSource;
import io.toolforge.toolforge4j.io.OutputSink;

public class NgramsTest {
  @Test
  public void smokeTest() {
    List<NgramCount> ngrams = App.compute(Stream.of("Hello, world!", "Hello, Dolly!",
        "Say hello to my little friend!", "You had me at hello."), 1, 3).toList();

    assertThat(ngrams,
        is(List.of(NgramCount.of("hello", 3), NgramCount.of("at", 1), NgramCount.of("had", 1),
            NgramCount.of("had me", 1), NgramCount.of("had me at", 1), NgramCount.of("hello to", 1),
            NgramCount.of("hello to my", 1), NgramCount.of("little", 1), NgramCount.of("me", 1),
            NgramCount.of("me at", 1), NgramCount.of("my", 1), NgramCount.of("my little", 1),
            NgramCount.of("say", 1), NgramCount.of("say hello", 1),
            NgramCount.of("say hello to", 1), NgramCount.of("to", 1), NgramCount.of("to my", 1),
            NgramCount.of("to my little", 1), NgramCount.of("you", 1), NgramCount.of("you had", 1),
            NgramCount.of("you had me", 1))));
  }

  @Test
  public void spreadsheetTest() throws Exception {
    File ngramsCsv = File.createTempFile("ngrams.", ".csv");
    ngramsCsv.deleteOnExit();

    File ngramsXlsx = File.createTempFile("ngrams.", ".xlsx");
    // ngramsXlsx.deleteOnExit();

    Configuration configuration = new Configuration();
    configuration.data = new InputSource(Resources.getResource("mlb_players_2021.csv").toURI());
    configuration.minNgramLength = 1L;
    configuration.maxNgramLength = 1L;
    configuration.textColumnName = "Name";
    configuration.ngramsCsv = new OutputSink(ngramsCsv.toURI());
    configuration.ngramsXlsx = new OutputSink(ngramsXlsx.toURI());

    App.main(configuration);

    String[][] expected = new String[][] {{"kyle", "24"}, {"nick", "22"}, {"ryan", "22"},
        {"jose", "20"}, {"jake", "18"}, {"luis", "18"}, {"tyler", "18"}, {"josh", "17"},
        {"matt", "17"}, {"andrew", "15"}, {"austin", "15"}, {"alex", "14"}, {"mike", "14"},
        {"chris", "13"}, {"michael", "12"}, {"aaron", "11"}, {"anthony", "11"}, {"brandon", "11"},
        {"david", "11"}, {"miguel", "10"}, {"daniel", "9"}, {"drew", "9"}, {"justin", "9"},
        {"trevor", "9"}, {"bryan", "8"}, {"joe", "8"}, {"jordan", "8"}, {"kevin", "8"},
        {"taylor", "8"}, {"adam", "7"}, {"carlos", "7"}, {"dylan", "7"}, {"garrett", "7"},
        {"luke", "7"}, {"sam", "7"}, {"scott", "7"}, {"zack", "7"}, {"aj", "6"}, {"brad", "6"},
        {"eric", "6"}, {"james", "6"}, {"jason", "6"}, {"john", "6"}, {"jonathan", "6"},
        {"max", "6"}, {"paul", "6"}, {"sean", "6"}, {"stephen", "6"}, {"tommy", "6"}, {"zach", "6"},
        {"cody", "5"}, {"danny", "5"}, {"jorge", "5"}, {"mitch", "5"}, {"patrick", "5"},
        {"seth", "5"}, {"steven", "5"}, {"tanner", "5"}, {"will", "5"}, {"blake", "4"},
        {"brett", "4"}, {"chad", "4"}, {"charlie", "4"}, {"christian", "4"}, {"corey", "4"},
        {"de", "4"}, {"hunter", "4"}, {"ian", "4"}, {"jesse", "4"}, {"jon", "4"}, {"lucas", "4"},
        {"pedro", "4"}, {"rafael", "4"}, {"travis", "4"}, {"victor", "4"}, {"adrian", "3"},
        {"alec", "3"}, {"andres", "3"}, {"angel", "3"}, {"ben", "3"}, {"brent", "3"},
        {"brian", "3"}, {"caleb", "3"}, {"cole", "3"}, {"connor", "3"}, {"derek", "3"},
        {"dillon", "3"}, {"dustin", "3"}, {"edwin", "3"}, {"evan", "3"}, {"francisco", "3"},
        {"greg", "3"}, {"gregory", "3"}, {"jacob", "3"}, {"jd", "3"}, {"jesus", "3"}, {"joey", "3"},
        {"juan", "3"}, {"logan", "3"}, {"manny", "3"}, {"pablo", "3"}, {"ramon", "3"},
        {"robert", "3"}, {"ronald", "3"}, {"shane", "3"}, {"spencer", "3"}, {"thomas", "3"},
        {"tim", "3"}, {"tj", "3"}, {"tony", "3"}, {"wade", "3"}, {"zac", "3"}, {"albert", "2"},
        {"andre", "2"}, {"andy", "2"}, {"antonio", "2"}, {"bailey", "2"}, {"bobby", "2"},
        {"bradley", "2"}, {"brady", "2"}, {"cal", "2"}, {"carson", "2"}, {"casey", "2"},
        {"chase", "2"}, {"chi", "2"}, {"colin", "2"}, {"corbin", "2"}, {"cristian", "2"},
        {"dan", "2"}, {"darren", "2"}, {"daulton", "2"}, {"devin", "2"}, {"dj", "2"},
        {"domingo", "2"}, {"dominic", "2"}, {"donovan", "2"}, {"edward", "2"}, {"eli", "2"},
        {"elvis", "2"}, {"emmanuel", "2"}, {"erik", "2"}, {"fernando", "2"}, {"gabe", "2"},
        {"gavin", "2"}, {"griffin", "2"}, {"harold", "2"}, {"hector", "2"}, {"humberto", "2"},
        {"hyun", "2"}, {"isaac", "2"}, {"jace", "2"}, {"jared", "2"}, {"javy", "2"}, {"jay", "2"},
        {"jeff", "2"}, {"jimmy", "2"}, {"josé", "2"}, {"jt", "2"}, {"julian", "2"}, {"julio", "2"},
        {"junior", "2"}, {"keegan", "2"}, {"la", "2"}, {"lance", "2"}, {"lewis", "2"},
        {"manuel", "2"}, {"marcus", "2"}, {"mark", "2"}, {"martin", "2"}, {"mauricio", "2"},
        {"mickey", "2"}, {"oliver", "2"}, {"pete", "2"}, {"peter", "2"}, {"randy", "2"},
        {"richard", "2"}, {"riley", "2"}, {"robbie", "2"}, {"ryne", "2"}, {"sandy", "2"},
        {"sergio", "2"}, {"trent", "2"}, {"tucker", "2"}, {"vladimir", "2"}, {"wander", "2"},
        {"wil", "2"}, {"wilmer", "2"}, {"wyatt", "2"}, {"yoan", "2"}, {"yu", "2"}, {"a", "1"},
        {"abraham", "1"}, {"acuna", "1"}, {"adalberto", "1"}, {"adbert", "1"}, {"adolis", "1"},
        {"adonis", "1"}, {"adrián", "1"}, {"akeem", "1"}, {"akil", "1"}, {"alan", "1"},
        {"alberto", "1"}, {"alcides", "1"}, {"aledmys", "1"}, {"alejandro", "1"}, {"alek", "1"},
        {"alexander", "1"}, {"alfonso", "1"}, {"amed", "1"}, {"amir", "1"}, {"andrelton", "1"},
        {"aramis", "1"}, {"archie", "1"}, {"aristides", "1"}, {"aroldis", "1"}, {"art", "1"},
        {"asher", "1"}, {"avisail", "1"}, {"bernardo", "1"}, {"billy", "1"}, {"blaine", "1"},
        {"bo", "1"}, {"brandyn", "1"}, {"braxton", "1"}, {"brendan", "1"}, {"breyvic", "1"},
        {"brock", "1"}, {"brody", "1"}, {"brooks", "1"}, {"bruce", "1"}, {"brusdar", "1"},
        {"bryce", "1"}, {"buck", "1"}, {"burch", "1"}, {"buster", "1"}, {"byron", "1"},
        {"cam", "1"}, {"cameron", "1"}, {"camilo", "1"}, {"carter", "1"}, {"cavan", "1"},
        {"cedric", "1"}, {"cesar", "1"}, {"chas", "1"}, {"chasen", "1"}, {"chaz", "1"},
        {"cionel", "1"}, {"cj", "1"}, {"clarke", "1"}, {"clayton", "1"}, {"clint", "1"},
        {"collin", "1"}, {"colten", "1"}, {"colton", "1"}, {"conner", "1"}, {"cooper", "1"},
        {"cortes", "1"}, {"cory", "1"}, {"craig", "1"}, {"cristopher", "1"}, {"curt", "1"},
        {"curtis", "1"}, {"dakota", "1"}, {"dallas", "1"}, {"damon", "1"}, {"dane", "1"},
        {"dansby", "1"}, {"darien", "1"}, {"darin", "1"}, {"darwinzon", "1"}, {"dauri", "1"},
        {"daz", "1"}, {"dean", "1"}, {"deivi", "1"}, {"del", "1"}, {"delino", "1"}, {"dellin", "1"},
        {"demarcus", "1"}, {"deolis", "1"}, {"didi", "1"}, {"dietrich", "1"}, {"dinelson", "1"},
        {"dom", "1"}, {"duane", "1"}, {"dusten", "1"}, {"eddy", "1"}, {"edgar", "1"},
        {"edmundo", "1"}, {"eduard", "1"}, {"eduardo", "1"}, {"ehire", "1"}, {"elias", "1"},
        {"elieser", "1"}, {"eloy", "1"}, {"emilio", "1"}, {"ender", "1"}, {"enoli", "1"},
        {"enrique", "1"}, {"erasmo", "1"}, {"erick", "1"}, {"ernie", "1"}, {"ervin", "1"},
        {"eugenio", "1"}, {"felix", "1"}, {"flores", "1"}, {"framber", "1"}, {"franchy", "1"},
        {"frankie", "1"}, {"franmil", "1"}, {"freddie", "1"}, {"freddy", "1"}, {"gary", "1"},
        {"genesis", "1"}, {"george", "1"}, {"geraldo", "1"}, {"gerardo", "1"}, {"german", "1"},
        {"gerrit", "1"}, {"giancarlo", "1"}, {"gilberto", "1"}, {"gio", "1"}, {"giovanny", "1"},
        {"glenn", "1"}, {"gleyber", "1"}, {"grant", "1"}, {"grayson", "1"}, {"guerrero", "1"},
        {"guillermo", "1"}, {"gurriel", "1"}, {"hans", "1"}, {"hanser", "1"}, {"harrison", "1"},
        {"haseong", "1"}, {"henry", "1"}, {"hernan", "1"}, {"hirokazu", "1"}, {"hoby", "1"},
        {"honeywell", "1"}, {"huascar", "1"}, {"hyeonjong", "1"}, {"isan", "1"}, {"isiah", "1"},
        {"jack", "1"}, {"jackie", "1"}, {"jackson", "1"}, {"jacoby", "1"}, {"jahmai", "1"},
        {"jaime", "1"}, {"jakob", "1"}, {"jameson", "1"}, {"jandel", "1"}, {"janson", "1"},
        {"jarlin", "1"}, {"jarred", "1"}, {"jarren", "1"}, {"jazz", "1"}, {"jb", "1"}, {"jc", "1"},
        {"jean", "1"}, {"jed", "1"}, {"jeffrey", "1"}, {"jefry", "1"}, {"jeimer", "1"},
        {"jerad", "1"}, {"jeremy", "1"}, {"jeurys", "1"}, {"jharel", "1"}, {"jhon", "1"},
        {"jhonathan", "1"}, {"jhoulys", "1"}, {"jiman", "1"}, {"jin", "1"}, {"jo", "1"},
        {"joan", "1"}, {"johan", "1"}, {"johnny", "1"}, {"jojo", "1"}, {"jonah", "1"},
        {"jordy", "1"}, {"joshua", "1"}, {"jovani", "1"}, {"jp", "1"}, {"jurickson", "1"},
        {"justus", "1"}, {"kaleb", "1"}, {"kean", "1"}, {"kebryan", "1"}, {"kenley", "1"},
        {"kent", "1"}, {"kenta", "1"}, {"keone", "1"}, {"kervin", "1"}, {"keston", "1"},
        {"ketel", "1"}, {"keury", "1"}, {"keynan", "1"}, {"kirby", "1"}, {"kodi", "1"},
        {"kohei", "1"}, {"kohl", "1"}, {"kolby", "1"}, {"kole", "1"}, {"kolten", "1"},
        {"konner", "1"}, {"kris", "1"}, {"kurt", "1"}, {"kutter", "1"}, {"kwang", "1"},
        {"lamonte", "1"}, {"lars", "1"}, {"leody", "1"}, {"leury", "1"}, {"lewin", "1"},
        {"liam", "1"}, {"ljay", "1"}, {"lorenzo", "1"}, {"lou", "1"}, {"louis", "1"},
        {"lourdes", "1"}, {"mac", "1"}, {"madison", "1"}, {"magneuris", "1"}, {"maikel", "1"},
        {"marcell", "1"}, {"marco", "1"}, {"marcos", "1"}, {"mason", "1"}, {"matthew", "1"},
        {"mccullers", "1"}, {"merrill", "1"}, {"miles", "1"}, {"mookie", "1"}, {"nabil", "1"},
        {"nate", "1"}, {"nathan", "1"}, {"nathaniel", "1"}, {"nestor", "1"}, {"nicky", "1"},
        {"nico", "1"}, {"niko", "1"}, {"nivaldo", "1"}, {"noah", "1"}, {"nolan", "1"},
        {"nomar", "1"}, {"odubel", "1"}, {"omar", "1"}, {"oscar", "1"}, {"owen", "1"},
        {"ozzie", "1"}, {"packy", "1"}, {"paolo", "1"}, {"pat", "1"}, {"pavin", "1"}, {"phil", "1"},
        {"phillip", "1"}, {"phillips", "1"}, {"pierce", "1"}, {"ponce", "1"}, {"preston", "1"},
        {"raimel", "1"}, {"raisel", "1"}, {"randal", "1"}, {"ranger", "1"}, {"raynel", "1"},
        {"reese", "1"}, {"reid", "1"}, {"reiss", "1"}, {"reiver", "1"}, {"renato", "1"},
        {"rex", "1"}, {"reyes", "1"}, {"reymin", "1"}, {"reynaldo", "1"}, {"rhys", "1"},
        {"richie", "1"}, {"rj", "1"}, {"roansy", "1"}, {"rob", "1"}, {"robel", "1"},
        {"roberto", "1"}, {"robinson", "1"}, {"rodolfo", "1"}, {"roel", "1"}, {"roman", "1"},
        {"romy", "1"}, {"rony", "1"}, {"ross", "1"}, {"rougned", "1"}, {"rowan", "1"},
        {"salvador", "1"}, {"sammy", "1"}, {"santiago", "1"}, {"sebastian", "1"}, {"seby", "1"},
        {"seranthony", "1"}, {"shawn", "1"}, {"shea", "1"}, {"shed", "1"}, {"sheldon", "1"},
        {"shogo", "1"}, {"shohei", "1"}, {"sonny", "1"}, {"souza", "1"}, {"spenser", "1"},
        {"starlin", "1"}, {"stefan", "1"}, {"steve", "1"}, {"stevie", "1"}, {"taijuan", "1"},
        {"tarik", "1"}, {"tatis", "1"}, {"tayler", "1"}, {"tejay", "1"}, {"teoscar", "1"},
        {"thairo", "1"}, {"todd", "1"}, {"tom", "1"}, {"tomas", "1"}, {"touki", "1"},
        {"trayce", "1"}, {"tres", "1"}, {"trey", "1"}, {"triston", "1"}, {"tucupita", "1"},
        {"ty", "1"}, {"tylor", "1"}, {"tyrone", "1"}, {"underwood", "1"}, {"vidal", "1"},
        {"vimael", "1"}, {"vinny", "1"}, {"walker", "1"}, {"webster", "1"}, {"wes", "1"},
        {"whit", "1"}, {"willi", "1"}, {"william", "1"}, {"willians", "1"}, {"willie", "1"},
        {"willson", "1"}, {"wily", "1"}, {"xander", "1"}, {"yadiel", "1"}, {"yadier", "1"},
        {"yandy", "1"}, {"yasmani", "1"}, {"yefry", "1"}, {"yency", "1"}, {"yennsy", "1"},
        {"yermin", "1"}, {"yohan", "1"}, {"yohel", "1"}, {"yonathan", "1"}, {"yonny", "1"},
        {"yordan", "1"}, {"yuli", "1"}, {"yusei", "1"}, {"yusmeiro", "1"}};

    for (File file : new File[] {ngramsXlsx}) {
      try (TabularWorksheetReader rows = SpreadsheetFactory.getInstance()
          .readActiveTabularWorksheet(() -> new FileInputStream(file))) {
        assertThat(rows.stream().map(row -> {
          return new String[] {
              row.findCellByColumnName("ngram").map(c -> c.getValue(String.class)).get(),
              row.findCellByColumnName("count").map(c -> c.getValue(Integer.class)).get()
                  .toString()};
        }).toArray(String[][]::new), is(expected));
      }
    }
  }
}
