package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import javax.annotation.Generated;

import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import antlr.collections.AST;
import antlr.collections.List;

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
  
  private class flagNode{
    DetailAST node;
    int flag;
    
    public flagNode() {
      node = new DetailAST();
      flag = 0;
    }
    
  }
  
  private flagNode generateTestTree() {
    
    ArrayList<flagNode> nodes  = new ArrayList<flagNode>();
    
    flagNode root = new flagNode();
    flagNode initNode = new flagNode();
    root.node.setFirstChild(initNode.node);
    boolean addChild = true;
    boolean atRoot = true;
    int parent = 0;
  
    for(int i=0;i<10;i++) {
      flagNode newNode = new flagNode();
      nodes.add(newNode);
      if(addChild==true) {
        if(atRoot == true) {
          nodes.add(new flagNode());
          nodes.add(new flagNode());
          nodes.add(new flagNode());

          root.node.setFirstChild(nodes.get(0).node);
          nodes.get(0).node.setNextSibling(nodes.get(1).node);
          nodes.get(1).node.setNextSibling(nodes.get(2).node);
          i=2;
        }
        else
          nodes.get(parent).node.setFirstChild(newNode.node);
          
        addChild = false;
      }
      
      //three children per parent
      if((i+1)%3==0) {
        addChild=true;
        DetailAST temp = nodes.get(i).node.getParent();
        if(atRoot == true) {
          parent = 0;
          atRoot = false;
        }
        else
          parent = nodes.indexOf(temp)+1; 
      }
      else {
        if(i>2) {
          nodes.get(i-1).node.setNextSibling(newNode.node);
        }
      }

    }
        
    return root;
  }
  

  @Test
  public void testRecursivelySearchAST() {
    //fail("Not yet implemented");
    flagNode root = generateTestTree();
  }

  @Test
  public void testClassifyNode() 
  {
    // Create a node to pass into classify node
    DetailAST node = new DetailAST();
    int temp = 0;
    int size = 0;
    
    node.setType(TokenTypes.COMMENT_CONTENT);
    temp = check.commentContents;
    check.classifyNode(node);
    if (check.commentContents != temp + 1) fail("commentContents was not incremented given a COMMENT_CONTENT node");
    
    node.setType(TokenTypes.SINGLE_LINE_COMMENT);
    temp = check.singleLineComments;
    check.classifyNode(node);
    if (check.singleLineComments != temp + 1) fail("singleLineComments was not incremented given a SINGLE_LINE_COMMENT node");
    
    node.setType(TokenTypes.BLOCK_COMMENT_BEGIN);
    temp = check.blockCommentBegins;
    check.classifyNode(node);
    if (check.blockCommentBegins != temp + 1) fail("blockCommentBegins was not incremented given a BLOCK_COMMENT_BEGIN node");
    
    node.setType(TokenTypes.BLOCK_COMMENT_END);
    temp = check.blockCommentEnds;
    check.classifyNode(node);
    if (check.blockCommentEnds != temp + 1) fail("blockCommentEnds was not incremented given a BLOCK_COMMENT_END node");
    
    node.setType(TokenTypes.LITERAL_WHILE);
    temp = check.loopCount;
    check.classifyNode(node);
    if (check.loopCount != temp + 1) fail("loopCount was not incremented given a LITERAL_WHILE node");
    
    node.setType(TokenTypes.VARIABLE_DEF);
    temp = check.variableCount;
    check.classifyNode(node);
    if (check.variableCount != temp + 1) fail("variableCount was not incremented given a VARIABLE_DEF node");
    
    node.setType(TokenTypes.EXPR);
    temp = check.expressionCount;
    check.classifyNode(node);
    if (check.expressionCount != temp + 1) fail("expressionCount was not incremented given a EXPR node");
    
    node.setType(TokenTypes.TYPECAST);
    temp = check.typecastCount;
    check.classifyNode(node);
    if (check.typecastCount != temp + 1) fail("typecastCount was not incremented given a TYPECAST node");
    
    node.setType(TokenTypes.BAND);
    temp = check.operatorCount;
    check.uniqueOperators = new HashSet<>();
    size = check.uniqueOperators.size();
    check.classifyNode(node);
    if (check.operatorCount != temp + 1) fail("operatorCount was not incremented given a BAND node");
    if (check.uniqueOperators.size() != size + 1) fail("uniqueOperators was not incremented given a BAND node"); 
    
    node.setType(TokenTypes.IDENT);
    temp = check.operandCount;
    check.uniqueOperands = new HashSet<>();
    size = check.uniqueOperands.size();
    check.classifyNode(node);
    if (check.operandCount != temp + 1) fail("operandCount was not incremented given a IDENT node");
    if (check.uniqueOperands.size() != size + 1) fail("uniqueOperands was not incremented given a IDENT node");
    
    node.setLineNo(10);
    check.programLength = 0;
    check.classifyNode(node);
    if (check.programLength != 10) fail("programLength was not incremented given a node with a larger line number");
    
    // TODO: Test (node.getType() == TokenTypes.METHOD_DEF) once correctly Mocking getIdentInChildren(node)
    
    node.setType(TokenTypes.LITERAL_IF);
    temp = check.cyclomaticComplexity;
    check.classifyNode(node);
    if (check.cyclomaticComplexity != temp + 1) fail("cyclomaticComplexity was not incremented given a LITERAL_IF node");
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
}
