package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberOfVariableDeclarations extends AbstractCheck {

  private int count = 0;

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


  @Override
  public void visitToken(DetailAST ast) {
    count = 0;
    // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
    // count the number of direct children of the OBJBLOCK
    // that are METHOD_DEFS
    int classVars = objBlock.getChildCount(TokenTypes.VARIABLE_DEF);
    count += classVars;
    /*
    DetailAST firstChild = objBlock.getFirstChild();
    if(firstChild != null) {

    }
    */
    
    
    log(ast.getLineNo(), "numberofvariabledeclarations", count);
    
  }
}
