package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.HashSet;

public class NewCheck extends AbstractCheck {
  // Properties
  private int maxlinesofcomments = 10;
  private int maxcomments = 10;  
  private int maxloops = 10;
  private int maxvariables = 10;
  private int maxexpressions = 10;
  private int maxoperators = 10;
  private int maxoperands = 10;
  private int maxtypecasts = 10;
  // Halstead
  private int maxhalsteadlength = 10;
  private int maxhalsteadvocabulary = 10;
  private int maxhalsteadvolume = 10;
  private int maxhalsteaddifficulty = 10;
  private int maxhalsteadeffort = 10;
  // Methods
  private int maxlocalmethodrefs = 10;
  private int maxexternalmethodrefs = 10;
  // Maintainability Index
  private int maxmaintainabilityindex = 0;
  
  // Counts
  private int commentContents = 0;
  private int singleLineComments = 0;
  private int blockCommentBegins = 0;
  private int blockCommentEnds = 0;
  // Misc
  private int loopCount = 0;  
  private int variableCount = 0;  
  private int expressionCount = 0;
  private int typecastCount = 0;
  // Halstead
  private int operatorCount = 0;
  private int operandCount = 0;
  private int halsteadLength = 0;
  private HashSet<Integer> uniqueOperators = new HashSet<>(); 
  private HashSet<String> uniqueOperands = new HashSet<>(); 
  private int halsteadVocabulary = 0;
  private int programLength = 0;
  private double halsteadVolume = 0;
  private double halsteadDifficulty = 0;
  private double halsteadEffort = 0;
  // Methods // TODO: Handle methods that are in secondary classes
  private HashSet<String> localMethods = new HashSet<>();
  private int localMethodRefsCount = 0;
  private int externalMethodRefsCount = 0;
  // Maintainability Index
  private int cyclomaticComplexity = 0;
  private double maintainabilityIndex = 0;

  // Overrides
  @Override
  public boolean isCommentNodesRequired() {
    return true;
  }
  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
  }
  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }
  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
  }

  // Setters: Automatically pull the configured limits from checkstyle
  // Semantics
  public void setMaxlinesofcomments(int limit) {
    maxlinesofcomments = limit;
  }  
  public void setMaxcomments(int limit) {
    maxcomments = limit;
  }  
  public void setMaxloops(int limit) {
    maxloops = limit;
  }  
  public void setMaxvariables(int limit) {
    maxvariables = limit;
  }
  public void setMaxexpressions(int limit) {
    maxexpressions = limit;
  }
  public void setMaxoperators(int limit) {
    maxoperators = limit;
  }
  public void setMaxoperands(int limit) {
    maxoperands = limit;
  }
  public void setMaxtypecasts(int limit) {
    maxtypecasts = limit;
  }
  // Halstead
  public void setMaxhalsteadlength(int limit) {
    maxhalsteadlength = limit;
  }
  public void setMaxhalsteadvocabulary(int limit) {
    maxhalsteadvocabulary = limit;
  }
  public void setMaxhalsteadvolume(int limit) {
    maxhalsteadvolume = limit;
  }
  public void setMaxhalsteaddifficulty(int limit) {
    maxhalsteaddifficulty = limit;
  }
  public void setMaxhalsteadeffort(int limit) {
    maxhalsteadeffort = limit;
  }
  // Methods
  public void setMaxlocalmethodrefs(int limit) {
    maxlocalmethodrefs = limit;
  }
  public void setMaxexternalmethodrefs(int limit) {
    maxexternalmethodrefs = limit;
  }  
  // Maintainability Index
  public void setMaxmaintainabilityindex(int limit) {
    maxmaintainabilityindex = limit;
  }
  
  
  // Visit Token is called by Checkstyle
  @Override
  public void visitToken(DetailAST ast) {
    DetailAST head = ast.getFirstChild();
    initializeCounts();
    recursivelySearchAST(head);
    lateRecursivelySearchAST(head);
    logErrors();
  }  
  
  // Initialize Counts will make sure that all counters are reset
  public void initializeCounts() {
    // Comments
    commentContents = 0;
    singleLineComments = 0;
    blockCommentBegins = 0;    
    blockCommentEnds = 0;   
    // Misc
    loopCount = 0;    
    variableCount = 0;    
    expressionCount = 0;
    typecastCount = 0;
    // Halstead
    operatorCount = 0;
    operandCount = 0;
    halsteadLength = 0;
    uniqueOperators = new HashSet<>(); 
    uniqueOperands = new HashSet<>(); 
    halsteadVocabulary = 0;
    programLength = 0;
    halsteadVolume = 0;
    halsteadDifficulty = 0;
    halsteadEffort = 0;
    // Methods
    localMethods = new HashSet<>();
    localMethodRefsCount = 0;
    externalMethodRefsCount = 0;
    // Maintainability Index
    cyclomaticComplexity = 0;
    maintainabilityIndex = 0;
  }
  
  // Recursively Search AST will search the entire AST classifying each node
  public void recursivelySearchAST(DetailAST node)
  {
    if (node != null)
    {
      try {
        classifyNode(node);
      } 
      catch (Exception e) {
        log(0, e.toString());
      }     
      
      if (node.getFirstChild() != null) {
        try {
          recursivelySearchAST(node.getFirstChild());
        }
        catch (Exception e) {
          log(0, e.toString());
        }
      }
      
      if (node.getNextSibling() != null) {
        recursivelySearchAST(node.getNextSibling());
      }              
    }
  }
  
  // Classify node classifies a provided node and increments relevant counters
  public void classifyNode(DetailAST node) {
    if (node.getType() == TokenTypes.COMMENT_CONTENT) {
      commentContents += 1;
    }
    
    if (node.getType() == TokenTypes.SINGLE_LINE_COMMENT) {
      singleLineComments += 1;
    }
    
    if (node.getType() == TokenTypes.BLOCK_COMMENT_BEGIN) {
       blockCommentBegins += 1;
    }
    
    if (node.getType() == TokenTypes.BLOCK_COMMENT_END) {
      blockCommentEnds += 1;
    }
    
    if (node.getType() == TokenTypes.LITERAL_WHILE || node.getType() == TokenTypes.LITERAL_FOR || node.getType() == TokenTypes.LITERAL_DO) {
      loopCount += 1;
    }

    if (node.getType() == TokenTypes.VARIABLE_DEF) {
      variableCount += 1;
    }    

    if (node.getType() == TokenTypes.EXPR) {
      expressionCount += 1;
    }
    
    if (node.getType() == TokenTypes.TYPECAST) {
      typecastCount += 1;
    }
    
    // Halstead
    // TODO: Make this less ugly
    if (node.getType() == TokenTypes.BAND || node.getType() == TokenTypes.BOR || node.getType() == TokenTypes.BSR || 
        node.getType() == TokenTypes.BXOR || node.getType() == TokenTypes.COLON || node.getType() == TokenTypes.DIV || 
        node.getType() == TokenTypes.EQUAL || node.getType() == TokenTypes.GE || node.getType() == TokenTypes.GT || 
        node.getType() == TokenTypes.LAND || node.getType() == TokenTypes.LE || node.getType() == TokenTypes.LITERAL_INSTANCEOF || 
        node.getType() == TokenTypes.LOR || node.getType() == TokenTypes.LT || node.getType() == TokenTypes.MINUS || 
        node.getType() == TokenTypes.MOD || node.getType() == TokenTypes.NOT_EQUAL || node.getType() == TokenTypes.PLUS || 
        node.getType() == TokenTypes.QUESTION || node.getType() == TokenTypes.SL || node.getType() == TokenTypes.SR || 
        node.getType() == TokenTypes.STAR || node.getType() == TokenTypes.ASSIGN || node.getType() == TokenTypes.BAND_ASSIGN ||
        node.getType() == TokenTypes.BOR_ASSIGN || node.getType() == TokenTypes.BSR_ASSIGN || node.getType() == TokenTypes.BXOR_ASSIGN ||
        node.getType() == TokenTypes.DIV_ASSIGN || node.getType() == TokenTypes.MINUS_ASSIGN || node.getType() == TokenTypes.MOD_ASSIGN ||
        node.getType() == TokenTypes.PLUS_ASSIGN || node.getType() == TokenTypes.SL_ASSIGN || node.getType() == TokenTypes.SR_ASSIGN || 
        node.getType() == TokenTypes.STAR_ASSIGN || node.getType() == TokenTypes.METHOD_REF) {
      operatorCount += 1;      
      uniqueOperators.add(node.getType());
    }
    
    if (node.getType() == TokenTypes.IDENT) {
      operandCount += 1;      
      uniqueOperands.add(node.getText());
    }
    
    if (node.getLineNo() > programLength) programLength = node.getLineNo();
   
    // Methods
    if (node.getType() == TokenTypes.METHOD_DEF) {
      localMethods.add(getIdentInChildren(node));
    }    
    
    // Calculate Cyclomatic Complexity as explained here 'https://www.javaworld.com/article/2074995/dealing-cyclomatic-complexity-in-java-code.html'
    if (node.getType() == TokenTypes.LITERAL_IF || node.getType() == TokenTypes.LITERAL_ELSE || node.getType() == TokenTypes.LITERAL_SWITCH || 
        node.getType() == TokenTypes.LITERAL_FOR || node.getType() == TokenTypes.LITERAL_WHILE || node.getType() == TokenTypes.LITERAL_DO || 
        node.getType() == TokenTypes.LITERAL_BREAK || node.getType() == TokenTypes.LITERAL_CONTINUE || node.getType() == TokenTypes.LITERAL_RETURN) {
      cyclomaticComplexity += 1;
    }      
  }
  
  // Late Recursive Search is used for tasks that require a second search over the AST
  public void lateRecursivelySearchAST(DetailAST node)
  {
    if (node != null)
    {
      try {
        lateProcessing(node);
      } 
      catch (Exception e) {
        log(0, e.toString());
      }     
      
      if (node.getFirstChild() != null) {
        try {
          lateRecursivelySearchAST(node.getFirstChild());
        }
        catch (Exception e) {
          log(0, e.toString());
        }
      }
      
      if (node.getNextSibling() != null) {
        lateRecursivelySearchAST(node.getNextSibling());
      }              
    }
  }
  
  // Late processing contains checks that rely on some product from the first AST search
  public void lateProcessing(DetailAST node)
  {
    // Here we rely on the first search to build a list of local methods to compare against
    if (node.getType() == TokenTypes.METHOD_CALL) {
      
      if (localMethods.contains(getIdentInChildren(node))) {
        localMethodRefsCount += 1;
      }
      else {
        externalMethodRefsCount += 1;
      }
    } 
  }
  
  // Get IDENT in Children will try to find an IDENT node in the children of a given node
  public String getIdentInChildren(DetailAST node)
  {
    node = node.getFirstChild();
    while (node != null)
    {
      if (node.getType() == TokenTypes.IDENT)
      {
        return node.getText();
      }
      node = node.getNextSibling();
    }
    return "";
  }
  
  // Log Errors checks for any rule violations and uses checkstyle "log()" to report them
  public void logErrors() {
    // Total Comment Lines
    int totalCommentLines = commentContents + (blockCommentBegins * 2) + blockCommentEnds;
    if (totalCommentLines > maxlinesofcomments) {
      log(0, "commentlineslimit", maxlinesofcomments, totalCommentLines);
    }    

    // Total Comments
    int totalComments = singleLineComments + blockCommentBegins;
    if (totalComments > maxcomments) {
      log(0, "commentlimit", maxcomments, totalComments);
    }

    // Loop keywords
    if (loopCount > maxloops) {
      log(0, "looplimit", maxloops, loopCount);
    }    

    // Total Variables
    if (variableCount > maxvariables) {
      log(0, "variablelimit", maxvariables, variableCount);
    }
    
    // Total Expressions
    if (expressionCount > maxexpressions) {
      log(0, "expressionlimit", maxexpressions, expressionCount);
    }
    
    // Total Casts
    if (typecastCount > maxtypecasts) {
      log(0, "typecastlimit", maxtypecasts, typecastCount);
    }
    
    // Total Operators
    if (operatorCount > maxoperators) {
      log(0, "operatorlimit", maxoperators, operatorCount);
    }
    
    // Total Operands
    if (operandCount > maxoperands) {
      log(0, "operandlimit", maxoperands, operandCount);
    }
    
    // Halstead Length
    halsteadLength = operatorCount + operandCount;
    if (halsteadLength > maxhalsteadlength) {
      log(0, "halsteadlengthlimit", maxhalsteadlength, halsteadLength);
    }
    
    // Halstead Vocabulary
    halsteadVocabulary = uniqueOperators.size() + uniqueOperands.size();
    if (halsteadVocabulary > maxhalsteadvocabulary) {
      log(0, "halsteadvocabularylimit", maxhalsteadvocabulary, halsteadVocabulary);
    }
    
    // Halstead Volume
    halsteadVolume = programLength * (Math.log(halsteadVocabulary) / Math.log(2));
    if (halsteadVolume > maxhalsteadvolume) {
      log(0, "halsteadvolumelimit", maxhalsteadvolume, halsteadVolume);
    }    
    
    // Halstead Difficulty
    halsteadDifficulty = ((double)uniqueOperators.size() / (double)2) * ((double)operandCount / (double)uniqueOperators.size());
    if (halsteadDifficulty > maxhalsteaddifficulty) {
      log(0, "halsteaddifficultylimit", maxhalsteaddifficulty, halsteadDifficulty);
    }
    
    // Halstead Effort
    halsteadEffort = halsteadDifficulty * halsteadVolume;
    if (halsteadEffort > maxhalsteadeffort) {
      log(0, "halsteadeffortlimit", maxhalsteadeffort, halsteadEffort);
    }  
    
    // Local Method Calls
    if (localMethodRefsCount > maxlocalmethodrefs) {
      log(0, "localmethodreflimit", maxlocalmethodrefs, localMethodRefsCount);
    }
    
    // External Method Calls
    if (externalMethodRefsCount > maxexternalmethodrefs) {
      log(0, "externalmethodreflimit", maxexternalmethodrefs, externalMethodRefsCount);
    }
    
    // Maintainability Index
    maintainabilityIndex = 171 - (5.2 * (Math.log(halsteadVolume) / Math.log(2))) - (0.23 * cyclomaticComplexity) -
                           (16.2 * (Math.log(programLength) / Math.log(2))) + (50 * Math.sin(Math.sqrt(2.4 * (totalCommentLines / programLength))));
    if (maintainabilityIndex > maxmaintainabilityindex) {
      log(0, "maintainabilityindexlimit", maxmaintainabilityindex, maintainabilityIndex);
    }      
  }
}
