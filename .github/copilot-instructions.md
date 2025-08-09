# Computer Science Education Repository (Aulas Graduação)

**ALWAYS follow these instructions first and only fallback to additional search and context gathering if the information here is incomplete or found to be in error.**

This is an educational repository containing examples and materials for computer science courses including programming, data science, databases, numerical methods, and modeling. The repository is designed for learning and contains code examples across multiple programming languages and technologies.

## Working Effectively

### Environment Setup
- **Java Development**: Java 17 is pre-installed and ready to use
  - `javac` for compilation
  - `java` for execution
  - Compile time: < 5 seconds per file
- **PHP Development**: PHP 8.3.6 is pre-installed and ready to use
  - `php` for script execution
  - Execution time: < 5 seconds per script
- **Python Development**: Python 3.12.3 is pre-installed
  - `python3` for script execution
  - Install Jupyter for notebook work: `pip3 install jupyter notebook numpy matplotlib pandas` (takes ~5 minutes, NEVER CANCEL)
- **Docker**: Available for containerized development
- **Composer**: Available for PHP dependency management
- **Ant**: Available for Java builds

### Repository Structure

```
├── programming/           # Programming language examples
│   ├── java/             # Basic Java concepts and examples
│   ├── java-ee/          # Java EE projects (NetBeans-based)
│   └── php/              # PHP development (concepts, OOP, web, database, PDF)
├── data-science/         # Data science and Python analysis
│   ├── python/           # Python notebooks for data analysis
│   └── analise-dados/    # Data analysis examples
├── banco-dados/          # Database-related content
│   ├── scripts-sql/      # SQL scripts
│   ├── modelagem-banco-dados/  # Database modeling
│   └── linguagem-procedure/    # Stored procedures
├── metodos-numericos/    # Numerical methods with Python notebooks
└── modelagem/            # UML and object-oriented modeling
    └── uml-poo/          # UML and OOP concepts
```

## Language-Specific Instructions

### Java Development
- **Basic Java Programs**: Located in `programming/java/`
  - Compile: `javac FileName.java`
  - Run: `java ClassName`
  - Build time: < 5 seconds. NEVER CANCEL.
- **Java EE Projects**: Located in `programming/java-ee/`
  - **LIMITATION**: NetBeans projects CANNOT be built without NetBeans IDE installed
  - Projects use Ant build system but require NetBeans CopyLibs library
  - DO NOT attempt to build NetBeans projects - they will fail with missing library errors
  - You can view and edit individual Java files but cannot build the complete projects

### PHP Development
- **Basic PHP Scripts**: Located in `programming/php/`
  - Execute: `php script-name.php`
  - Execution time: < 5 seconds. NEVER CANCEL.
- **Composer Projects**: Some projects have `composer.json`
  - **KNOWN ISSUE**: Legacy projects may have PHP version conflicts
  - Current environment has PHP 8.3.6 but some projects require older versions
  - If `composer install` fails with version conflicts, document the incompatibility
- **Docker Projects**: Some PHP projects include Dockerfile
  - **KNOWN ISSUE**: The PDF PHP project Docker build fails due to Xdebug compatibility
  - Build time: 2-5 minutes when successful. NEVER CANCEL during Docker builds.

### Python Development
- **Basic Python Scripts**: Execute with `python3 script-name.py`
- **Jupyter Notebooks**: Located in `data-science/python/` and `metodos-numericos/`
  - Install dependencies first: `pip3 install jupyter notebook numpy matplotlib pandas`
  - Installation time: ~5 minutes. NEVER CANCEL.
  - **LIMITATION**: Many notebooks contain `input()` calls that prevent automated execution
  - DO NOT attempt to execute notebooks with user input programmatically
  - Use `jupyter notebook` to run interactively (not possible in this environment)
  - For validation, check that notebooks open without syntax errors

## Validation and Testing

### For Java Changes:
1. Navigate to the Java file directory
2. Compile: `javac FileName.java`
3. Run: `java ClassName` 
4. Verify expected output

### For PHP Changes:
1. Navigate to the PHP file directory  
2. Execute: `php script-name.php`
3. Verify expected output
4. For web applications, test individual PHP files rather than full applications

### For Python Changes:
1. Execute: `python3 script-name.py`
2. For notebooks: Verify JSON syntax with:
   ```bash
   python3 -c "
   import json
   with open('notebook.ipynb', 'r') as f:
       json.load(f)
   print('Notebook syntax valid')
   "
   ```
3. **DO NOT** run notebooks with `input()` calls in automated mode

### For Documentation Changes:
- No special validation required
- Ensure Markdown syntax is correct
- Check that links to examples are valid

## Common Tasks

### Adding New Java Examples:
1. Create `.java` file in appropriate `programming/java/` subdirectory
2. Follow existing naming conventions
3. Include class documentation as per existing examples
4. Test compilation and execution
5. Add simple `main` method for demonstration

### Adding New PHP Examples:
1. Create `.php` file in appropriate `programming/php/` subdirectory
2. Follow existing code structure
3. Test execution with `php filename.php`
4. Ensure examples are self-contained

### Adding New Python Examples:
1. For scripts: Create `.py` file in appropriate directory
2. For notebooks: Create `.ipynb` file
3. **AVOID** `input()` calls for automated validation
4. Include necessary imports at the top
5. Test basic syntax validation

### Working with Database Examples:
- SQL scripts in `banco-dados/scripts-sql/` can be viewed and edited
- **NO DATABASE VALIDATION**: No database server is available for testing
- Focus on SQL syntax correctness

## Known Limitations and Issues

1. **NetBeans Java EE Projects**: Cannot build without NetBeans IDE
2. **Interactive Notebooks**: Cannot execute notebooks with `input()` calls automatically
3. **Legacy PHP Dependencies**: Some Composer projects have version conflicts with PHP 8.3.6
4. **Docker PHP Project**: Build fails due to Xdebug installation issues
5. **No Database Server**: Cannot test SQL scripts against actual databases
6. **No CI/CD**: Repository has no automated testing infrastructure

## File System Reference

### Key directories content:
```bash
# Programming examples
ls programming/java/00-conceitos/01_Tipos\ de\ dados/
# Contains: TiposDados.java, TestaCaracteres.java, etc.

ls programming/php/01-conceitos-php/01/
# Contains: 01-ola-mundo.php, 05-strings.php, etc.

ls metodos-numericos/
# Contains: 01 - Introdução_Python-mn.ipynb, 02 - Noções_de_erros.ipynb, etc.
```

### Sample working commands:
```bash
# Compile and run Java
cd programming/java/00-conceitos/01_Tipos\ de\ dados/
javac TiposDados.java && java TiposDados

# Run PHP script  
cd programming/php/01-conceitos-php/01/
php 01-ola-mundo.php

# Install Python dependencies
pip3 install jupyter notebook numpy matplotlib pandas

# Basic Python execution
python3 -c "print('Hello World')"
```

## Scenario Testing

### End-to-End Validation Workflow:
When making changes to the repository, follow this validation sequence:

1. **For Java Changes**:
   ```bash
   cd programming/java/00-conceitos/01_Tipos\ de\ dados/
   javac TiposDados.java && java TiposDados
   # Should output: "Minha idade é 5", "Minha altura é 1.85", etc.
   ```

2. **For PHP Changes**:
   ```bash
   cd programming/php/01-conceitos-php/01/
   php 01-ola-mundo.php
   # Should output: "Olá mundo!"
   ```

3. **For Python Notebook Changes**:
   ```bash
   cd metodos-numericos/
   python3 -c "import json; json.load(open('01 - Introdução_Python-mn.ipynb')); print('Valid')"
   # Should output: "Valid"
   ```

4. **Verify Known Limitations**:
   ```bash
   # NetBeans projects should fail with CopyLibs error
   cd programming/java-ee/Aula\ 05/HelloWorldNetBeans/
   ant compile  # Expected to fail with "libs.CopyLibs.classpath property is not set up"
   ```

## Timeout Guidelines
- **Java compilation**: Set 30 seconds timeout (actual: < 5 seconds)
- **PHP execution**: Set 30 seconds timeout (actual: < 5 seconds)
- **Python package installation**: Set 10 minutes timeout (actual: ~5 minutes). NEVER CANCEL.
- **Docker builds**: Set 10 minutes timeout (actual: 2-5 minutes when working). NEVER CANCEL.
- **Notebook syntax checking**: Set 60 seconds timeout

**CRITICAL**: This is an educational repository. Focus on individual file compilation and execution rather than complex build processes. Most validation should verify that examples work correctly for learning purposes.