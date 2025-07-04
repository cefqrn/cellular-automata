.PHONY: clean run

target=main.jar

srcdir=src
bindir=bin

sources=$(shell find $(srcdir) -name '*.java')
classes=$(sources:$(srcdir)/%.java=$(bindir)/%.class)

.SECONDARY: $(bindir) $(classes)

$(target): Manifest.txt $(classes)
	jar cmf $< $(target) $(patsubst $(bindir)/%,-C $(bindir) '%',$(shell find $(bindir) -name '*.class'))

$(bindir)/%.class: $(srcdir)/%.java | $(bindir)
	javac -d $(bindir) -sourcepath $(srcdir) $<

$(bindir):
	mkdir $(bindir)

clean:
	rm -f $(target)
	rm -rf $(bindir)

run: $(target)
	java -jar $<
