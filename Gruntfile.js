'use strict';
var fs = require('fs');

var parseString = require('xml2js').parseString;
// Returns the second occurence of the version number
var parseVersionFromPomXml = function() {
    var version;
    var pomXml = fs.readFileSync('pom.xml', "utf8");
    parseString(pomXml, function (err, result){
        version = result.project.version[0];
    });
    return version;
};

module.exports = function (grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        bower_concat: {
            all: {
                dest: 'src/main/webapp/build/bower.js',
                cssDest: 'src/main/webapp/build/bower.css',
                mainFiles: {
                    'bootstrap': ['dist/css/bootstrap.css']
                }
            }
        },
        concat: {
            js: {
                options: {
                    separator: ';\n'
                },
                src: ['src/main/webapp/build/*.js', 'src/main/webapp/src/app/*.js', 'src/main/webapp/src/app/**/*.js'],
                dest: 'src/main/webapp/dist/app.js'
            },
            css: {
                options: {
                    separator: '\n'
                },
                src: ['src/main/webapp/build/*.css', 'src/main/webapp/src/assets/css/*.css'],
                dest: 'src/main/webapp/dist/css/styles.css'
            }
        },
        copy: {
            dist: {
                files: [{
                    // Bootstrap fonts
                    expand: true,
                    cwd: 'bower_components/bootstrap/dist',
                    src: 'fonts/*',
                    dest: 'src/main/webapp/dist/'
                }]
            }
        },
        jshint: {
            all: ['src/w/*.js', 'src/main/webapp/src/app/**/*.js']
        },
        html2js: {
            options: {
                base: "src/main/webapp",
                module: "keytrinket-templates"
            },
            dist: {
                src: [ 'src/main/webapp/src/app/**/*.html' ],
                dest: 'src/main/webapp/build/templates.js'
            }
        },
        clean: {
            temp: {
                src: [ 'src/main/webapp/build' ]
            }
        }
    });

    grunt.loadNpmTasks('grunt-bower-concat');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-html2js');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-copy');

    grunt.registerTask('dev', [
        'jshint',
        'bower_concat',
        'html2js',
        'concat',
        'copy',
        'clean'
    ]);

    grunt.registerTask('default', ['dev']);
};
