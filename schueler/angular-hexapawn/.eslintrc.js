module.exports = {
  env: {
    browser: true,
    es6: true,
    node: true,
    jasmine: true,
  },
  extends: [
    'plugin:@typescript-eslint/recommended',
    'plugin:@typescript-eslint/recommended-requiring-type-checking',
    'prettier/@typescript-eslint',
    'plugin:prettier/recommended',
  ],
  parser: '@typescript-eslint/parser',
  parserOptions: {
    project: 'tsconfig.json',
    sourceType: 'module',
  },
  plugins: ['@typescript-eslint'],
  rules: {
    '@typescript-eslint/adjacent-overload-signatures': 'error',
    '@typescript-eslint/array-type': 'off',
    '@typescript-eslint/ban-types': 'error',
    '@typescript-eslint/class-name-casing': 'error',
    '@typescript-eslint/consistent-type-assertions': 'error',
    '@typescript-eslint/explicit-function-return-type': 'off',
    '@typescript-eslint/member-delimiter-style': [
      'error',
      {
        multiline: {
          delimiter: 'semi',
          requireLast: true,
        },
        singleline: {
          delimiter: 'semi',
          requireLast: false,
        },
      },
    ],
    '@typescript-eslint/member-ordering': 'warn',
    '@typescript-eslint/no-empty-function': 'off',
    '@typescript-eslint/no-empty-interface': 'error',
    '@typescript-eslint/no-explicit-any': 'off',
    '@typescript-eslint/no-inferrable-types': 'error',
    '@typescript-eslint/no-misused-new': 'error',
    '@typescript-eslint/no-namespace': 'error',
    '@typescript-eslint/no-non-null-assertion': 'error',
    '@typescript-eslint/no-parameter-properties': 'off',
    '@typescript-eslint/no-use-before-define': 'off',
    '@typescript-eslint/no-var-requires': 'off',
    '@typescript-eslint/prefer-for-of': 'error',
    '@typescript-eslint/prefer-function-type': 'error',
    '@typescript-eslint/prefer-namespace-keyword': 'error',
    '@typescript-eslint/quotes': [
      'error',
      'single',
      { avoidEscape: true, allowTemplateLiterals: true },
    ],
    '@typescript-eslint/triple-slash-reference': 'error',
    '@typescript-eslint/unified-signatures': 'error',
    'arrow-body-style': 'error',
    camelcase: 'error',
    complexity: 'off',
    'constructor-super': 'error',
    curly: 'error',
    'dot-notation': 'error',
    eqeqeq: ['error', 'smart'],
    'guard-for-in': 'error',
    'id-blacklist': [
      'error',
      'any',
      'Number',
      'number',
      'String',
      'string',
      'Boolean',
      'boolean',
      'Undefined',
      'undefined',
    ],
    'id-match': 'error',
    'max-classes-per-file': 'off',
    'max-len': [
      'error',
      {
        code: 140,
      },
    ],
    'new-parens': 'error',
    'no-bitwise': 'error',
    'no-caller': 'error',
    'no-cond-assign': 'error',
    'no-console': [
      'error',
      {
        allow: [
          'log',
          'warn',
          'dir',
          'timeLog',
          'assert',
          'clear',
          'count',
          'countReset',
          'group',
          'groupEnd',
          'table',
          'dirxml',
          'error',
          'groupCollapsed',
          'Console',
          'profile',
          'profileEnd',
          'timeStamp',
          'context',
        ],
      },
    ],
    'no-debugger': 'error',
    'no-eval': 'error',
    'no-invalid-this': 'off',
    'no-new-wrappers': 'error',
    'no-restricted-imports': ['error', 'rxjs/Rx'],
    'no-shadow': [
      'error',
      {
        hoist: 'all',
      },
    ],
    'no-throw-literal': 'error',
    'no-trailing-spaces': 'warn',
    'no-undef-init': 'error',
    'no-underscore-dangle': 'error',
    'no-unused-expressions': 'error',
    'no-var': 'error',
    'object-shorthand': 'error',
    'one-var': ['error', 'never'],
    'prefer-const': 'error',
    'prettier/prettier': 'warn',
    'quote-props': ['error', 'as-needed'],
    radix: 'error',
    'space-before-function-paren': [
      'error',
      {
        anonymous: 'never',
        asyncArrow: 'always',
        named: 'never',
      },
    ],
    'spaced-comment': 'error',
  },
};