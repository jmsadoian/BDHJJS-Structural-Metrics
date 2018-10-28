package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NewCheckTest {
  NewCheck check = new NewCheck();
  
  @Test
  public void testInitializeCounts() {
    // Setup non-zero values for all NewCheck Counters
    HashSet<Integer> intHashSet1 = new HashSet<>();
    intHashSet1.add(1);
    HashSet<String> strHashSet1 = new HashSet<>();
    strHashSet1.add("STR1");
    HashSet<String> strHashSet2 = new HashSet<>();
    strHashSet2.add("STR2");
    
    // Comments
    check.commentContents = 15;
    check.singleLineComments = 100;
    check.blockCommentBegins = 11;    
    check.blockCommentEnds = 23;   
    // Misc
    check.loopCount = 100;    
    check.variableCount = 50;    
    check.expressionCount = 45;
    check.typecastCount = 22;
    // Halstead
    check.operatorCount = 42;
    check.operandCount = 50;
    check.halsteadLength = 60;
    check.uniqueOperators = intHashSet1; 
    check.uniqueOperands = strHashSet1;
    check.halsteadVocabulary = 70;
    check.programLength = 90;
    check.halsteadVolume = 1000;
    check.halsteadDifficulty = 120;
    check.halsteadEffort = 121;
    // Methods
    check.localMethods = strHashSet2;
    check.localMethodRefsCount = 120;
    check.externalMethodRefsCount = 121;
    // Maintainability Index
    check.cyclomaticComplexity = 500;
    check.maintainabilityIndex = 50;
    
    // Run Method
    check.initializeCounts();
    
    // Check for errors and build error string
    String completeErrorMessage = "\n";
    
    // Comments
    if (check.commentContents != 0)         completeErrorMessage += "commentContents did not reset to 0\n";
    if (check.singleLineComments != 0)      completeErrorMessage += "singleLineComments did not reset to 0\n";
    if (check.blockCommentBegins != 0)      completeErrorMessage += "blockCommentBegins did not reset to 0\n";
    if (check.blockCommentEnds != 0)        completeErrorMessage += "blockCommentEnds did not reset to 0\n";
                                             
    // Misc
    if (check.loopCount != 0)               completeErrorMessage += "loopCount did not reset to 0\n";
    if (check.variableCount != 0)           completeErrorMessage += "variableCount did not reset to 0\n";
    if (check.expressionCount != 0)         completeErrorMessage += "expressionCount did not reset to 0\n";
    if (check.typecastCount != 0)           completeErrorMessage += "typecastCount did not reset to 0\n";
                                             
    // Halstead
    if (check.operatorCount != 0)           completeErrorMessage += "operatorCount did not reset to 0\n";
    if (check.operandCount != 0)            completeErrorMessage += "variableCount did not reset to 0\n";
    if (check.halsteadLength != 0)          completeErrorMessage += "halsteadLength did not reset to 0\n";
    if (check.uniqueOperators.size() != 0)  completeErrorMessage += "uniqueOperators did not reset to an empty HashSet\n";
    if (check.uniqueOperands.size() != 0)   completeErrorMessage += "uniqueOperands did not reset to an empty HashSet\n";
    if (check.halsteadVocabulary != 0)      completeErrorMessage += "halsteadVocabulary did not reset to 0\n";
    if (check.programLength != 0)           completeErrorMessage += "programLength did not reset to 0\n";
    if (check.halsteadVolume != 0)          completeErrorMessage += "halsteadVolume did not reset to 0\n";
    if (check.halsteadDifficulty != 0)      completeErrorMessage += "halsteadDifficulty did not reset to 0\n";
    if (check.halsteadEffort != 0)          completeErrorMessage += "halsteadEffort did not reset to 0\n";
                                             
    // Methods
    if (check.localMethods.size() != 0)     completeErrorMessage += "localMethods did not reset to an empty HashSet\n";
    if (check.localMethodRefsCount != 0)    completeErrorMessage += "localMethodRefsCount did not reset to 0\n";
    if (check.externalMethodRefsCount != 0) completeErrorMessage += "externalMethodRefsCount did not reset to 0\n";
                                             
    // Maintainability Index
    if (check.cyclomaticComplexity != 0)    completeErrorMessage += "cyclomaticComplexity did not reset to 0\n";
    if (check.maintainabilityIndex != 0)    completeErrorMessage += "maintainabilityIndex did not reset to 0\n";
    
    if (completeErrorMessage.length() > 1) fail(completeErrorMessage);
  }

  @Test
  public void testRecursivelySearchAST() {
    fail("Not yet implemented");
  }

  @Test
  public void testClassifyNode() 
  {    
    fail("Not yet implemented");
  }

  @Test
  public void testLateRecursivelySearchAST() {
    fail("Not yet implemented");
  }

  @Test
  public void testLateProcessing() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIdentInChildren() {
    fail("Not yet implemented");
  }

  @Test
  public void testLogErrors() {
    fail("Not yet implemented");
  }

}
