package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.RootModule;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberOfComments extends AbstractCheck {


  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.EOF };
  }

  @Override
  public int[] getRequiredTokens() {
	  return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
	  return new int[] { TokenTypes.EOF };
  }

  @Override
  public void visitToken(DetailAST ast) {
    int count = 0;
    
    //DetailAST root = ast.getParent();
    // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
    count += ast.getChildCount(TokenTypes.SINGLE_LINE_COMMENT);
    count += ast.getChildCount(TokenTypes.BLOCK_COMMENT_BEGIN);

    //DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
    // count the number of direct children of the OBJBLOCK
    
    //int classComments = objBlock.getChildCount(TokenTypes.SINGLE_LINE_COMMENT);
    
    log(ast.getLineNo(), "numberofcomments", count);
    
  }
}
