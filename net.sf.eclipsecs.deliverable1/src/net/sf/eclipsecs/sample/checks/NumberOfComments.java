package net.sf.eclipsecs.sample.checks;

import java.io.Console;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.RootModule;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//not complete 
public class NumberOfComments extends AbstractCheck {


  @Override
  public int[] getAcceptableTokens() {
    System.out.println("in getAcceptableTokens");
    return getRequiredTokens();
  }

  @Override
  public int[] getRequiredTokens() {
    System.out.println("in getRequiredTokens");
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    System.out.println("in getDefaultTokens");
    return getRequiredTokens();
  }

  @Override
  public void visitToken(DetailAST ast) {
    System.out.println("in visitToken");
    int count = 0;
    
    //DetailAST root = ast.getParent();
    // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
    count += ast.getChildCount(TokenTypes.SINGLE_LINE_COMMENT);

    //DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
    // count the number of direct children of the OBJBLOCK
    
    //int classComments = objBlock.getChildCount(TokenTypes.SINGLE_LINE_COMMENT);
    System.out.println(count);
    log(ast.getLineNo(), "numberofcomments", count);
    
  }
}
