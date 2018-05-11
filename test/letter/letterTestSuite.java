package letter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  ConstantTest.class,
  VariableTest.class,
  LetterTest.class,
  LetterComparatorTest.class,
  LetterFactoryTest.class,
  LatexAdapterTest.class
})
public class letterTestSuite {
    // the class remains completely empty,
    // being used only as a holder for the above annotations
}
