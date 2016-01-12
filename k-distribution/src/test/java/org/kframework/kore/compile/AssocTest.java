// Copyright (c) 2015-2016 K Team. All Rights Reserved.

package org.kframework.kore.compile;

import org.junit.Test;
import org.junit.rules.TestName;
import org.kframework.attributes.Source;
import org.kframework.builtin.BooleanUtils;
import org.kframework.definition.Module;
import org.kframework.definition.Rule;
import org.kframework.kore.K;
import org.kframework.kore.KORE;
import org.kframework.kore.KVariable;
import org.kframework.kore.convertors.TstTinyOnKORE_IT;
import org.kframework.parser.ProductionReference;
import org.kframework.rewriter.Rewriter;
import org.kframework.rewriter.SearchType;
import org.kframework.unparser.AddBrackets;
import org.kframework.unparser.KOREToTreeNodes;
import org.kframework.utils.KoreUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class AssocTest {

    @org.junit.Rule
    public TestName name = new TestName();

    protected File testResource(String baseName) throws URISyntaxException {
        return new File(TstTinyOnKORE_IT.class.getResource(baseName).toURI());
    }

    @Test
    public void simple() throws IOException, URISyntaxException {
        String filename = "/compiler-tests/assoc-test.k";
        KoreUtils utils = new KoreUtils(filename, "ASSOC-TEST", "ASSOC-TEST");
        System.out.println(utils.compiledDef.executionModule().sentences().mkString("\n"));

        K kResult = utils.stepRewrite(utils.getParsed("0", Source.apply("generated by " + getClass().getSimpleName())), Optional.<Integer>empty());

        Module unparsingModule = utils.getUnparsingModule();

        String actual = KOREToTreeNodes.toString(new AddBrackets(unparsingModule).addBrackets((ProductionReference) KOREToTreeNodes.apply(KOREToTreeNodes.up(unparsingModule, kResult), unparsingModule)));

        assertEquals("Execution failed", "<k> 1 </k>", actual);
    }
}
